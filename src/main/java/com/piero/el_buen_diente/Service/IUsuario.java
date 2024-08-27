package com.piero.el_buen_diente.Service;

import com.piero.el_buen_diente.model.entity.Usuario;

public interface IUsuario {

    Usuario save(Usuario usuario);

    Usuario findById(Integer id);

    void deleteById(Integer id);
}
