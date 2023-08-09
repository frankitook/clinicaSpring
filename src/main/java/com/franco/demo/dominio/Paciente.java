package com.franco.demo.dominio;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pacientes")
public class Paciente {

    @Id
    private int dniPaciente;

    private String nombre;
    private String apellido;
    private String email;
    private String contrasena;
    private String obrasocial;

    public Paciente() {
    }

    public Paciente(int dniPaciente, String nombre, String apellido, String email, String contrasena,
            String obrasocial) {
        this.dniPaciente = dniPaciente;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.contrasena = contrasena;
        this.obrasocial = obrasocial;
    }

    

    public Paciente(int dniPaciente, String nombre, String apellido, String email, String contrasena) {
        this.dniPaciente = dniPaciente;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.contrasena = contrasena;
    }

    public int getDniPaciente() {
        return dniPaciente;
    }

    public void setDniPaciente(int dniPaciente) {
        this.dniPaciente = dniPaciente;
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

    public String getObrasocial() {
        return obrasocial;
    }

    public void setObrasocial(String obrasocial) {
        this.obrasocial = obrasocial;
    }

    

    
    
}
