package com.piero.el_buen_diente.Service.impl;

import com.piero.el_buen_diente.Service.IPaciente;
import com.piero.el_buen_diente.model.dao.PacienteDao;
import com.piero.el_buen_diente.model.entity.Paciente;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PacienteService implements IPaciente {

    private final Logger logger = LoggerFactory.getLogger(PacienteService.class);

    @Autowired
    private PacienteDao pacienteDao;

    @Transactional
    @Override
    public Paciente save(Paciente paciente) {
        logger.info(paciente.toString());
        return pacienteDao.save(paciente);
    }

    @Transactional(readOnly = true)
    @Override
    public Paciente findById(int id) {
        return pacienteDao.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Paciente> findAll() {
        return (List<Paciente>) pacienteDao.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Paciente> findPacienteNomApel(String nom, String apel) {

        if(nom != null && !nom.isEmpty()){
            logger.info("nombre");
            return (List<Paciente>) pacienteDao.findByNombreStartingWithIgnoreCase(nom);
        }
        else{
            logger.info("demas");
            return (List<Paciente>) pacienteDao.findByApellidoStartingWithIgnoreCase(apel);
        }

    }


    @Transactional
    @Override
    public void deleteById(int id) {
        pacienteDao.deleteById(id);
    }

    @Transactional
    @Override
    public boolean existsById(int id) {
        return pacienteDao.existsById(id);
    }

}
