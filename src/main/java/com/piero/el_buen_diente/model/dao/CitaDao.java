package com.piero.el_buen_diente.model.dao;

import com.piero.el_buen_diente.model.entity.Cita;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CitaDao extends CrudRepository<Cita, Integer> {

    List<Cita> findByEstadoContainingIgnoreCase(String estado);
}
