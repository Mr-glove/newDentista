package com.piero.el_buen_diente.Service;

import com.piero.el_buen_diente.model.entity.Usuario;

import java.util.List;

public interface IUsuario {

    Usuario save(Usuario usuario);

    Usuario findById(Integer id);

    void deleteById(Integer id);

    List<Usuario> findAll();

    boolean existsById(Integer id);
}
