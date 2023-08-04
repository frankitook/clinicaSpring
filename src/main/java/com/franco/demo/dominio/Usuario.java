package com.franco.demo.dominio;

import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "clientes")
public class Usuario {
    
    @Id
    private int dni;
    private String nombre;
    private String apellido;
    private String email;
    private String contrasena;
    private String tipo;

    public Usuario() {
    }

  

    // Getters y Setters

    public Usuario(int dni, String nombre, String apellido, String email, String contrasena, String tipo) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.contrasena = contrasena;
        this.tipo = tipo;
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



    public String getTipo() {
        return tipo;
    }



    public void setTipo(String tipo) {
        this.tipo = tipo;
    }



    public int getId() {
        return dni;
    }

    public void setId(int id) {
        this.dni = id;
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
}