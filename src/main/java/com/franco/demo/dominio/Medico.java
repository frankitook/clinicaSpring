package com.franco.demo.dominio;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "medicos")
public class Medico {
    

    @Id
    private int dniMedico;

    private String nombre;
    private String apellido;
    private String email;
    private String contrasena;    
    private String especialidad;

    @OneToMany(mappedBy="medico",
    orphanRemoval = true,
    cascade = CascadeType.ALL )
    private List<HorarioMedico> horarios = new ArrayList<HorarioMedico>();

    public Medico() {
    }

    public Medico(int dniMedico, String nombre, String apellido, String email, String contrasena, String especialidad) {
        this.dniMedico = dniMedico;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.contrasena = contrasena;
        this.especialidad = especialidad;
    }

    

    public Medico(int dniMedico, String nombre, String apellido, String email, String contrasena) {
        this.dniMedico = dniMedico;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.contrasena = contrasena;
    }

    

    public int getDniMedico() {
        return dniMedico;
    }

    public void setDniMedico(int dniMedico) {
        this.dniMedico = dniMedico;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public List<HorarioMedico> getHorarios() {
        return horarios;
    }

    public void setHorarios(List<HorarioMedico> horarios) {
        this.horarios = horarios;
    }

    

    


}
