package com.piero.el_buen_diente.controller;

import com.piero.el_buen_diente.Service.impl.CitaService;
import com.piero.el_buen_diente.Service.impl.PacienteService;
import com.piero.el_buen_diente.Service.impl.UsuarioService;
import com.piero.el_buen_diente.model.entity.Cita;
import com.piero.el_buen_diente.model.entity.Paciente;
import com.piero.el_buen_diente.model.entity.Usuario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Controller
public class WebControllerPaciente {

    Logger logger = LoggerFactory.getLogger(WebControllerPaciente.class);

    @Autowired
    private PacienteService pacienteService;

    @Autowired
    CitaService citaService;

    private final UsuarioService usuarioService;

    public WebControllerPaciente(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/prueba")
    public String prueba(){
        return "prueba";
    }

    //Login

    @GetMapping("/inicio")
    public String inicio() {

        return "inicio";
    }



    @GetMapping("/login")
    public String Login(Model model){
        return "login";
    }

    @GetMapping("/usuario")
    public String Usuario(Model model){
        logger.info("pagina para crear nuevo usuario");
        model.addAttribute("usuario", new Usuario());
        return "usuario";
    }
    @PostMapping("/usuario/nuevo")
    public String newUsuario(@ModelAttribute Usuario usuario) {
        logger.info("CREANDO NUEVO USUARIO");
        usuario.setIdUsuario(1);
        usuarioService.save(usuario);
        logger.info(usuario.toString());
        return "inicio";
    }


    //Pacientes

    // Redirigue para crear un paciente
    @GetMapping("/paciente/nuevo")
    public String preCreatePaciente(Model model) {
        model.addAttribute("paciente", new Paciente());
        return "new_paciente";
    }

    // Crear paciente
    @PostMapping("/paciente/nuevo")
    public String createPaciente(@ModelAttribute Paciente paciente) {
        pacienteService.save(paciente);
        logger.info(paciente.toString());
        return "redirect:/pacientes";
    }

    // Redirigue para ver a los pacientes
    @GetMapping("/pacientes")
    public String readPacientes(Model model) {
        List<Paciente> pacientes = pacienteService.findAll();
        model.addAttribute("pacientes", pacientes);
        return "pacientes";
    }

    @GetMapping("/pacientes/nombre")
    public String PacientesByNombre(@RequestParam(required = false) String nombre,
                                    @RequestParam(required = false) String apellido,
                                    Model model){
        List<Paciente> pacientes = pacienteService.findPacienteNomApel(nombre, apellido);
        model.addAttribute("pacientes", pacientes);
        return "pacientes";
    }


    // Redirigue a la pagina para editar los paciente
    @GetMapping("/paciente/editar/{id}")
    public String updatePaciente(@PathVariable int id,Model model) {
        Paciente p = pacienteService.findById(id);
        model.addAttribute("paciente", p);
        return "update_paciente";
    }

    // Borrar Pacientes
    @GetMapping("/paciente/borrar/{id}")
    public String deletePaciente(@PathVariable int id, Model model) {
        pacienteService.deleteById(id);
        return "redirect:/pacientes";
    }


    //CITA

    // redirigue para crear una cita ("new_cita")
    @GetMapping("/cita/nuevo")
    public String preCreateCita(Model model) {

        Cita cita = new Cita();
        cita.setFecha(LocalDate.now());
        cita.setHora(LocalTime.now());
        model.addAttribute("cita", cita);
        List<Paciente> paciente = pacienteService.findAll();
        model.addAttribute("pacientes",paciente);
        return "new_cita";
    }

    // Crea la cita y rediirgue a "/citas"
    @PostMapping("/cita/nuevo")
    public String createCita(@ModelAttribute Cita cita){
        logger.info(cita.toString());
        citaService.save(cita);
        return "redirect:/citas";
    }


    // Redirigue para ver las "citas_pendientes"
    @GetMapping("/citas/pendientes")
    public String readCitasPendientes(Model model){
        List<Cita> citas = citaService.findByEstado("pendiente");
        model.addAttribute("citas", citas);
        return "citas_pendientes";
    }

    //Redirigue para ver la pagina de "citas"
    @GetMapping("/citas")
    public String ordenarCitas(@RequestParam(name = "ordenar", required = false, defaultValue = "fecha") String ordenar,
                               @RequestParam(name = "direccion", required = false, defaultValue = "desc") String direccion,
                               Model model){

        List<Cita> citas = citaService.findInicio(ordenar, direccion);


        model.addAttribute("citas", citas);
        model.addAttribute("ordenar", ordenar);
        model.addAttribute("sortDir", direccion);

        String nuevaDir = direccion.equals("asc") ? "desc" : "asc";
        model.addAttribute("nuevaDir",nuevaDir);

        return "citas";
    }

    @GetMapping("/citas/paciente/{id}")
    public String buscarCitasPaciente(@PathVariable int id, Model model){
        List<Cita> citas = citaService.findByPacienteIdPaciente(id);
        model.addAttribute("citas", citas);
        return "citas";

    }

    // Redirigue a la pagina "citas", pero usando los filtros
    @GetMapping("/citas/buscar")
    public String buscarCitas(
                            @RequestParam(required = false) String nombre,
                            @RequestParam(required = false) String apellido,
                            @RequestParam(required = false) Integer mes,
                            @RequestParam(required = false) Integer año,
                            @RequestParam(required = false) String motivo,
                            @RequestParam(required = false) String estado,
                            @RequestParam(required = false) Double monto,
                            Model model) {

        List<Cita> citas = citaService.buscarCita(nombre, apellido, mes, año, motivo, estado, monto);
        model.addAttribute("citas", citas);

        return "citas";
    }



    // "cita -> cita Atendida"
    @GetMapping("/cita/atendido/{id}")
    public String citaToAtendido(@PathVariable int id, Model model){
        Cita cita = citaService.findById(id);
        cita.setEstado("Atendido");
        citaService.save(cita);
        return "redirect:/citas/pendientes";
    }

    // "cita -> No vino"
    @GetMapping("/cita/novino/{id}")
    public String citaToNoVino(@PathVariable int id, Model model){
        Cita cita = citaService.findById(id);
        cita.setEstado("No Vino");
        citaService.save(cita);
        return "redirect:/citas/pendientes";
    }

    // Redirigue para editar una cita
    @GetMapping("/cita/editar/{id}")
    public String updateCita(@PathVariable int id, Model model){
        Cita cita = citaService.findById(id);
        model.addAttribute("cita", cita);
        return "update_cita";
    }


    // Borrar la cita
    @GetMapping("/cita/borrar/{id}")
    public String deleteCita(@PathVariable int id){
        citaService.deleteById(id);
        return "redirect:/citas";
    }




    @GetMapping("/header")
    public String Header(){
        return "header";
    }

    /*
    - Login (para ingresar a la página)
    - ver_pacientes (para poder ver a todos los pacientes)
    - crear_paciente (para crear y modificar pacientes)
    - ver_citasPendientes (para solo vizualisar citas pendientes)
    - ver_todasCitas (para poder ver todas las citas)
    - crear_cita (para poder crear cita y modifiarCita)


    <---------------HEADER----------->
    - Imagen de un diente xd
    - pacientes
    - citas pendientes
    - todas las citas
    - Cerrar Sesion
     */

}
