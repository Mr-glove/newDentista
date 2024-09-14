package com.piero.el_buen_diente.model.dao;

import com.piero.el_buen_diente.model.entity.Usuario;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;

public interface UsuarioDao extends CrudRepository<Usuario, Integer> {

    Optional<Usuario> findByNombre(String usuario);
}
