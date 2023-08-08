package com.franco.demo.dominio;

import java.sql.Date;
import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "turnos")
public class Turno {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_turno;

    @ManyToOne
    @JoinColumn(name = "dniPaciente")
    private Usuario paciente;

    @ManyToOne
    @JoinColumn(name = "dniMedico")
    private Usuario medico;

    
    
    @Column(name = "fechaAtencion")
    private Date fechaAtencion;

    @Column(name = "horaAtencion")
    private Time horaAtencion;

    @Column(name = "estado")
    private String estado;

    public Turno() {
    }

    

    public Turno(Usuario paciente, Usuario medico, Date fechaAtencion, Time horaAtencion, String estado) {
        this.paciente = paciente;
        this.medico = medico;
        this.fechaAtencion = fechaAtencion;
        this.horaAtencion = horaAtencion;
        this.estado = estado;
    }




    public Turno(int idTurno,Usuario paciente,Usuario medico, Date fechaAtencion, Time horaAtencion,String estado) {
        this.estado = estado;
        this.fechaAtencion = fechaAtencion;
        this.horaAtencion = horaAtencion;
        this.id_turno= idTurno;
        this.medico = medico;
        this.paciente = paciente;
    }

    public int getIdTurno() {
        return id_turno;
    }

    public void setIdTurno(int idTurno) {
        this.id_turno = idTurno;
    }

    public Usuario getPaciente() {
        return paciente;
    }

    public void setPaciente(Usuario paciente) {
        this.paciente = paciente;
    }

    public Usuario getMedico() {
        return medico;
    }

    public void setMedico(Usuario medico) {
        this.medico = medico;
    }

    public Date getFechaAtencion() {
        return fechaAtencion;
    }

    public void setFechaAtencion(Date fechaAtencion) {
        this.fechaAtencion = fechaAtencion;
    }

    public Time getHoraAtencion() {
        return horaAtencion;
    }

    public void setHoraAtencion(Time horaAtencion) {
        this.horaAtencion = horaAtencion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

  

   
    


}
