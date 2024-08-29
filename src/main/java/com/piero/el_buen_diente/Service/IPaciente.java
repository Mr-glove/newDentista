package com.piero.el_buen_diente.Service;

import com.piero.el_buen_diente.model.entity.Paciente;

import java.util.List;

public interface IPaciente {

    Paciente save(Paciente paciente);

    Paciente findById(int id);

    List<Paciente> findAll();

    void deleteById(int id);

    boolean existsById(int id);

}
