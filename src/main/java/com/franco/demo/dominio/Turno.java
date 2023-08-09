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
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "dniMedico")
    private Medico medico;

    
    
    @Column(name = "fechaAtencion")
    private Date fechaAtencion;

    @Column(name = "horaAtencion")
    private Time horaAtencion;

    @Column(name = "estado")
    private String estado;

    public Turno() {
    }

    


    public Turno(Paciente paciente, Medico medico, Date fechaAtencion, Time horaAtencion, String estado) {
        this.paciente = paciente;
        this.medico = medico;
        this.fechaAtencion = fechaAtencion;
        this.horaAtencion = horaAtencion;
        this.estado = estado;
    }




    public Turno(int idTurno,Paciente paciente,Medico medico, Date fechaAtencion, Time horaAtencion,String estado) {
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

    

    public Paciente getPaciente() {
        return paciente;
    }




    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }




    public Medico getMedico() {
        return medico;
    }




    public void setMedico(Medico medico) {
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
