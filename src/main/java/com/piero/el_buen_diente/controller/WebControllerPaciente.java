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

    //Login

    @GetMapping("/login")
    public String Login(Model model){
        /*Usuario usuario = new Usuario();
        usuario.setUsuario("prueba1");
        usuario.setContraseña("prueba2");
        usuarioService.save(usuario);
        logger.info(usuario.toString());*/
        return "login";
    }

    @GetMapping("/usuario")
    public String Usuario(Model model){
        model.addAttribute("usuario", new Usuario());
        return "usuario";
    }

    @PostMapping("/usuario/nuevo")
    public String createUsuario(@ModelAttribute Usuario usuario){


        return "/pacientes";
    }




    //Pacientes

    @GetMapping("/paciente/nuevo")
    public String preCreatePaciente(Model model) {
        model.addAttribute("paciente", new Paciente());
        return "new_paciente";
    }

    @PostMapping("/paciente/nuevo")
    public String createPaciente(@ModelAttribute Paciente paciente) {
        pacienteService.save(paciente);
        logger.info(paciente.toString());
        return "redirect:/pacientes";
    }

    @GetMapping("/pacientes")
    public String readPacientes(Model model) {
        List<Paciente> pacientes = pacienteService.findAll();
        model.addAttribute("pacientes", pacientes);
        return "pacientes";
    }


    @GetMapping("/paciente/editar/{id}")
    public String updatePaciente(@PathVariable int id,Model model) {
        Paciente p = pacienteService.findById(id);
        model.addAttribute("paciente", p);
        return "update_paciente";
    }

    @GetMapping("/paciente/borrar/{id}")
    public String deletePaciente(@PathVariable int id, Model model) {
        pacienteService.deleteById(id);
        return "redirect:/pacientes";
    }


    //CITA

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

    @PostMapping("/cita/nuevo")
    public String createCita(@ModelAttribute Cita cita){
        logger.info(cita.toString());
        citaService.save(cita);
        return "redirect:/citas";
    }

    @GetMapping("/citas")
    public String readCita(Model model){
        List<Cita> citas = citaService.findAll();
        model.addAttribute("citas", citas);
        return "citas";
    }

    @GetMapping("/citas/pendientes")
    public String readCitasPendientes(Model model){
        List<Cita> citas = citaService.findByEstado("pendiente");
        model.addAttribute("citas", citas);
        return "citas_pendientes";
    }
    @GetMapping("/cita/atendido/{id}")
    public String citaToAtendido(@PathVariable int id, Model model){
        Cita cita = citaService.findById(id);
        cita.setEstado("Atendido");
        citaService.save(cita);
        return "redirect:/citas/pendientes";
    }

    @GetMapping("/cita/editar/{id}")
    public String updateCita(@PathVariable int id, Model model){
        Cita cita = citaService.findById(id);
        model.addAttribute("cita", cita);
        return "update_cita";
    }

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
