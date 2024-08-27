package com.piero.el_buen_diente.model.dao;

import com.piero.el_buen_diente.model.entity.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioDao extends CrudRepository<Usuario, Integer> {
}
