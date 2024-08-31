package com.piero.el_buen_diente.controller;

import com.piero.el_buen_diente.Service.impl.PacienteService;
import com.piero.el_buen_diente.model.entity.Paciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class WebController {

    @Autowired
    private PacienteService pacienteService;

    @GetMapping("/pacientes")
    public String viewPacientes(Model model) {
        List<Paciente> pacientes = pacienteService.findAll();
        model.addAttribute("pacientes", pacientes);
        return "pacientes";
    }

    @GetMapping("/pacientes/borrar/{id}")
    public String deletePaciente(@PathVariable int id, Model model) {
        pacienteService.deleteById(id);
        return "redirect:/pacientes";
    }

    @GetMapping("/pacientes/borrar/{id}")
    public String updatePaciente(@PathVariable int id,Model model) {
        Paciente p = pacienteService.findById(id);
        model.addAttribute("paciente", p);
        return "editar_paciente";
    }

    @GetMapping("/paciente/nuevo")
    public String showNewPaciente(Model model) {
        model.addAttribute("paciente", new Paciente());
        return "crear_paciente";
    }


    @PostMapping("/paciente/nuevo")
    public String newPaciente(@ModelAttribute Paciente paciente) {
        pacienteService.save(paciente);
        return "redirect:/pacientes";
    }
}
