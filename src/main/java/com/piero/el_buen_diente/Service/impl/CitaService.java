package com.piero.el_buen_diente.Service.impl;

import com.piero.el_buen_diente.Service.ICita;
import com.piero.el_buen_diente.model.dao.CitaDao;
import com.piero.el_buen_diente.model.entity.Cita;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CitaService implements ICita {

    @Autowired
    private CitaDao citaDao;

    @Transactional
    @Override
    public Cita save(Cita cita) {
        return citaDao.save(cita);
    }

    @Transactional(readOnly = true)
    @Override
    public Cita findById(int id) {
        return citaDao.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Cita> findAll() {
        return (List<Cita>) citaDao.findAll();
    }

    @Transactional
    @Override
    public void deleteById(int id) {
        citaDao.deleteById(id);
    }

    @Transactional
    @Override
    public boolean existsById(int id) {
        return citaDao.existsById(id);
    }
}
