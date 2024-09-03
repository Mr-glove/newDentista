package com.piero.el_buen_diente.Service;

import com.piero.el_buen_diente.model.entity.Cita;

import java.util.List;

public interface ICita{

    Cita save(Cita cita);

    Cita findById(int id);

    List<Cita> findAll();

    List<Cita> findByEstado(String estado);

    void deleteById(int id);

    boolean existsById(int id);


}
