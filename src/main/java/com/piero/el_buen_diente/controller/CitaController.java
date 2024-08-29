package com.piero.el_buen_diente.controller;

import com.piero.el_buen_diente.Service.ICita;
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
    private ICita usuarioService;

    @PostMapping("/cita")
    @ResponseStatus(HttpStatus.CREATED)
    public Cita create(@RequestBody Cita cita) {
        return usuarioService.save(cita);
    }

    @GetMapping("/citas")
    @ResponseStatus(HttpStatus.OK)
    public void readAll(){
        usuarioService.findAll();
    }

    @GetMapping("/cita/{id}")
    public Cita readById(@PathVariable int id) {
        return usuarioService.findById(id);
    }

    @PutMapping("/cita/{id}")
    public Cita update(@PathVariable int id, @RequestBody Cita cita) {
        if(usuarioService.existsById(id)){
            cita.setIdcita(id);
            return usuarioService.save(cita);
        }else{
            return null;
        }
    }

    @DeleteMapping("/cita/{id}")
    public void delete(@PathVariable int id) {
        usuarioService.deleteById(id);
    }

}
