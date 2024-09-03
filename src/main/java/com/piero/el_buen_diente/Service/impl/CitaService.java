package com.piero.el_buen_diente.Service.impl;

import com.piero.el_buen_diente.Service.ICita;
import com.piero.el_buen_diente.model.dao.CitaDao;
import com.piero.el_buen_diente.model.entity.Cita;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CitaService implements ICita {

    Logger logger = LoggerFactory.getLogger(CitaService.class);

    @Autowired
    private CitaDao citaDao;

    @Transactional
    @Override
    public Cita save(Cita cita) {
        logger.info(cita.toString());
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

    @Override
    public List<Cita> findByEstado(String estado) {
        return (List<Cita>) citaDao.findByEstadoContainingIgnoreCase(estado);
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
