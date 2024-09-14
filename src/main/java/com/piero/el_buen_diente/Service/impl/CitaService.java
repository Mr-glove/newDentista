package com.piero.el_buen_diente.Service.impl;

import com.piero.el_buen_diente.Service.ICita;
import com.piero.el_buen_diente.model.dao.CitaDao;
import com.piero.el_buen_diente.model.entity.Cita;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

@Service
public class CitaService implements ICita {

    Logger logger = LoggerFactory.getLogger(CitaService.class);

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
    public List<Cita> findInicio(String campo, String direccion) {
        Sort sort = direccion.equalsIgnoreCase("asc") ? Sort.by(Sort.Direction.ASC, campo) : Sort.by(Sort.Direction.DESC, campo);

        return (List<Cita>) citaDao.findByAño(LocalDate.now().getYear(),sort);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Cita>  findByAño(int año){
        Sort sort = Sort.by(Sort.Direction.DESC, "fecha");

        return (List<Cita>) citaDao.findByAño(año,sort);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Cita> buscarCita(String nombre, String apellido, Integer mes, Integer año, String motivo, String estado, Double monto) {
        List<Cita> list;

        if(nombre != null){
            logger.info("EJECUTANDO NOMBRE");
            list = citaDao.findByNombreContainingIgnoreCase(nombre);
        }else if(apellido != null){
            logger.info("EJECUTANDO Apellido");
            list = citaDao.findByApellidoContainingIgnoreCase(apellido);
        }
        else if(mes != null && año == null){
            logger.info("EJECUTANDO SOLO MES");
            list = citaDao.findByMes(mes);
        }
        else if(año != null && mes == null){
            logger.info("EJECUTANDO SOLO AÑO");
            list = citaDao.findByAño(año);
        }
        else if(mes != null && año != null){
            logger.info("EJECUTANDO MES Y AÑO ["+mes+"]["+año+"]");
            list = citaDao.findByMesAndAño(mes,año);
        }
        else if(motivo != null && !motivo.trim().isEmpty()){
            logger.info("MOTIVO");
            list = citaDao.findByMotivo(motivo);
        }
        else if(estado != null && !estado.trim().isEmpty()){
            logger.info("EJECUTANDO Estado: ["+estado+"]");
            list = citaDao.findByEstado(estado);
        }
        else if(monto != null && monto > 0 ){
            logger.info("EJECUTANDO monto");
            list = citaDao.findByMonto(monto);
        }
        else{
            list = citaDao.findByFiltros(nombre,apellido, mes, año, motivo, estado, monto);
        }

        list.sort(Comparator.comparing(Cita::getFecha).reversed());
        return list;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Cita> findAll() {
        return (List<Cita>) citaDao.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Cita> findByPacienteIdPaciente(Integer pacienteId) {
        return (List<Cita>) citaDao.findByPacienteIdPaciente(pacienteId);
    }

    @Override
    public List<Cita> findByEstado(String estado) {
        return (List<Cita>) citaDao.findByEstado(estado);
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
