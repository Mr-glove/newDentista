package com.piero.el_buen_diente.model.dao;

import com.piero.el_buen_diente.model.entity.Cita;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CitaDao extends CrudRepository<Cita, Integer> {


    List<Cita> findByMotivo(String motivo);

    List<Cita> findByEstado(String estado);

    List<Cita> findByMonto(Double monto);

    List<Cita> findByPacienteIdPaciente(Integer pacienteId);

    @Query(value = "SELECT * FROM cita WHERE MONTH(fecha) = :mes AND YEAR(fecha) = :año", nativeQuery = true)
    List<Cita> findByMesAndAño(@Param("mes") int mes, @Param("año") int año);

    @Query(value="SELECT * FROM cita WHERE cita.nombre = :nombre", nativeQuery = true)
    List<Cita> findByNombreContainingIgnoreCase(@Param("nombre") String nombre);

    @Query(value="SELECT * FROM cita WHERE cita.apellido = :apellido", nativeQuery = true)
    List<Cita> findByApellidoContainingIgnoreCase(@Param("apellido") String apellido);

    @Query(value = "SELECT * FROM cita WHERE MONTH(cita.fecha) = :mes", nativeQuery = true)
    List<Cita> findByMes(@Param("mes") int mes);

    @Query(value = "SELECT * FROM cita WHERE YEAR(cita.fecha) = :año", nativeQuery = true)
    List<Cita> findByAño(@Param("año")int año);

    @Query(value = "SELECT * FROM cita WHERE YEAR(cita.fecha) = :año", nativeQuery = true)
    List<Cita> findByAño(@Param("año")int año, Sort sort);




    @Query("SELECT c FROM Cita c JOIN c.paciente p WHERE "
            + "(LOWER(p.nombre) LIKE LOWER(CONCAT('%', :nombre, '%')) OR :nombre IS NULL) "
            + "AND (LOWER(p.apellido) LIKE LOWER(CONCAT('%', :apellido, '%')) OR :apellido IS NULL) "
            + "AND (MONTH(c.fecha) = :mes OR :mes IS NULL) "
            + "AND (YEAR(c.fecha) = :año OR :año IS NULL) "
            + "AND (LOWER(c.motivo) LIKE LOWER(CONCAT('%', :motivo, '%')) OR :motivo IS NULL) "
            + "AND (LOWER(c.estado) = LOWER(:estado) OR :estado IS NULL) "
            + "AND (c.monto = :monto OR :monto IS NULL)")
    List<Cita> findByFiltros(
            @Param("nombre") String nombre,
            @Param("apellido") String apellido,
            @Param("mes") Integer mes,
            @Param("año") Integer año,
            @Param("motivo") String motivo,
            @Param("estado") String estado,
            @Param("monto") Double monto
    );

    List<Cita> findAll(Sort sort);
}
