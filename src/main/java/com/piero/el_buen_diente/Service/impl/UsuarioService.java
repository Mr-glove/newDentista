package com.piero.el_buen_diente.Service.impl;

import com.piero.el_buen_diente.Service.IUsuario;
import com.piero.el_buen_diente.model.dao.UsuarioDao;
import com.piero.el_buen_diente.model.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService implements IUsuario {


    private final UsuarioDao usuarioDao;


    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioDao usuarioDao, PasswordEncoder passwordEncoder) {
        this.usuarioDao = usuarioDao;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<Usuario> findByUsuario(String usuario) {
        return usuarioDao.findByUsuario(usuario);
    }


    @Transactional
    @Override
    public Usuario save(Usuario usuario) {
        usuario.setContraseña(passwordEncoder.encode(usuario.getContraseña()));
        return usuarioDao.save(usuario);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Usuario> findAll() {
        return (List<Usuario>) usuarioDao.findAll();
    }



    @Transactional(readOnly = true)
    @Override
    public Usuario findById(Integer id) {
        return usuarioDao.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void deleteById(Integer id) {
        usuarioDao.deleteById(id);
    }

    @Override
    public boolean existsById(Integer id) {
        return usuarioDao.existsById(id);
    }
}
