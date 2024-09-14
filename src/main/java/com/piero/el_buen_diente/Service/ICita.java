package com.piero.el_buen_diente.Service;

import com.piero.el_buen_diente.model.entity.Cita;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface ICita{

    Cita save(Cita cita);

    Cita findById(int id);

    List<Cita> findInicio(String campo, String direccion);

    List<Cita> findByAño(int año);

    List<Cita> buscarCita(String nombre, String apellido, Integer mes, Integer año, String motivo, String estado, Double monto);

    List<Cita> findAll();

    List<Cita> findByPacienteIdPaciente(Integer pacienteId);

    List<Cita> findByEstado(String estado);

    void deleteById(int id);

    boolean existsById(int id);



}
