package com.piero.el_buen_diente.Service.impl;

import com.piero.el_buen_diente.Service.IUsuario;
import com.piero.el_buen_diente.model.dao.UsuarioDao;
import com.piero.el_buen_diente.model.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsuarioService implements IUsuario {

    @Autowired
    private UsuarioDao usuarioDao;


    @Transactional
    @Override
    public Usuario save(Usuario usuario) {
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
