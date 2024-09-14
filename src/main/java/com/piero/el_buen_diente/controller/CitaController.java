package com.piero.el_buen_diente.controller;

import com.piero.el_buen_diente.Service.ICita;
import com.piero.el_buen_diente.Service.impl.CitaService;
import com.piero.el_buen_diente.model.entity.Cita;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CitaController {

    private final Logger logger = LoggerFactory.getLogger(CitaController.class);

    @Autowired
    private CitaService citaService;

    @PostMapping("/cita")
    @ResponseStatus(HttpStatus.CREATED)
    public Cita create(@RequestBody Cita cita) {
        return citaService.save(cita);
    }

    @GetMapping("/citas")
    @ResponseStatus(HttpStatus.OK)
    public void readAll(){
        citaService.findAll();
    }

    @GetMapping("/cita/{id}")
    public Cita readById(@PathVariable int id) {
        return citaService.findById(id);
    }

    @PutMapping("/cita/{id}")
    public Cita update(@PathVariable int id, @RequestBody Cita cita) {
        if(citaService.existsById(id)){
            cita.setIdcita(id);
            return citaService.save(cita);
        }else{
            return null;
        }
    }

    @DeleteMapping("/cita/{id}")
    public void delete(@PathVariable int id) {
        citaService.deleteById(id);
    }

}
