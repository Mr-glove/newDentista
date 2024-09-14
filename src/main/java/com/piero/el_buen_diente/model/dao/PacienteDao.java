package com.piero.el_buen_diente.model.dao;

import com.piero.el_buen_diente.model.entity.Paciente;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface PacienteDao extends CrudRepository<Paciente, Integer> {

    List<Paciente> findByNombreStartingWithIgnoreCase(String nombre);

    List<Paciente> findByApellidoStartingWithIgnoreCase(String apellido);
    }
