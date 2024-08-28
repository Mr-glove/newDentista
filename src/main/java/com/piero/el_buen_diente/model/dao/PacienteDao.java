package com.piero.el_buen_diente.model.dao;

import com.piero.el_buen_diente.model.entity.Paciente;
import org.springframework.data.repository.CrudRepository;

public interface PacienteDao extends CrudRepository<Paciente, Integer> {
}
