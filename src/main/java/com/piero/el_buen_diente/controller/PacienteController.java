package com.piero.el_buen_diente.controller;

import com.piero.el_buen_diente.Service.impl.PacienteService;
import com.piero.el_buen_diente.model.entity.Paciente;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PacienteController {

    private Logger logger = LoggerFactory.getLogger(PacienteController.class);

    @Autowired
    private PacienteService pacienteService;


    @PostMapping("/paciente")
    @ResponseStatus(HttpStatus.CREATED)
    public Paciente create(@RequestBody Paciente paciente) {
        logger.info(paciente.toString());
        return pacienteService.save(paciente);
    }

    @GetMapping("/paciente/{id}")
    public Paciente readById(@PathVariable int id) {
        return pacienteService.findById(id);
    }

    @GetMapping("/pacientes")
    public List<Paciente> readAll() {
        return pacienteService.findAll();
    }

    @PutMapping("/paciente/{id}")
    public Paciente update(@PathVariable int id, @RequestBody Paciente paciente) {
        if(pacienteService.existsById(id)){
            paciente.setIdPaciente(id);
            return pacienteService.save(paciente);
        }else{
            return null;
        }
    }

    @DeleteMapping("/paciente/{id}")
    public void delete(@PathVariable int id) {
        pacienteService.deleteById(id);
    }
}
