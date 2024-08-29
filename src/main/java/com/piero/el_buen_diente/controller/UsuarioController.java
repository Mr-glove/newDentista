package com.piero.el_buen_diente.controller;

import com.piero.el_buen_diente.Service.IUsuario;
import com.piero.el_buen_diente.model.entity.Usuario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class UsuarioController {

    private final Logger logger = LoggerFactory.getLogger(UsuarioController.class);

    @Autowired
    private IUsuario usuarioService;

    @PostMapping("/usuario")
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario create(@RequestBody Usuario usuario) {
        return usuarioService.save(usuario);

    }

    @GetMapping("/usuario/{id}")
    public Usuario readById(@PathVariable int id) {
        return usuarioService.findById(id);
    }

    @GetMapping("/usuarios")
    public void readAll(){
        usuarioService.findAll();

    }

    @PutMapping("/usuario/{id}")
    public Usuario update(@PathVariable int id, @RequestBody Usuario usuario) {

        if(usuarioService.existsById(id)){
            usuario.setIdUsuario(id);
            return usuarioService.save(usuario);
        }else{

            return null;
        }
    }

    @DeleteMapping("/usuario/{id}")
    public void delete(@PathVariable int id) {
        usuarioService.deleteById(id);
    }
}
