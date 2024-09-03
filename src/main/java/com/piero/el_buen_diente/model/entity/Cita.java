package com.piero.el_buen_diente.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "cita")
public class Cita implements Serializable {

    @Id
    @Column(name = "idCita")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idcita;

    @ManyToOne
    @JoinColumn(name = "id_paciente", referencedColumnName = "idPaciente")
    private Paciente paciente;

    @Column(name = "fecha")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fecha;

    @Column(name = "hora")
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime hora;

    @Column(name = "motivo")
    private String motivo;

    @Column(name = "estado")
    private String estado;

    @Column(name = "nota")
    private String nota;

    @Column(name = "monto")
    private double monto;

}
