package com.franco.demo.dominio;





import javax.persistence.Column;
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

    @Column(name = "obraSocial")
    private String obrasocial;
    
    private String especialidad;


    

    // Getters y Setters

    public Usuario() {
    }



    public Usuario(int dni, String nombre, String apellido, String email, String contrasena, String tipo,
            String obrasocial, String especialidad) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.contrasena = contrasena;
        this.tipo = tipo;
        this.obrasocial = obrasocial;
        this.especialidad = especialidad;
    }



    public Usuario(int dni, String nombre, String apellido, String email, String contrasena, String tipo) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.contrasena = contrasena;
        this.tipo = tipo;
    }

    
    
    public String getObrasocial() {
        return obrasocial;
    }



    public void setObrasocial(String obrasocial) {
        this.obrasocial = obrasocial;
    }



    public String getEspecialidad() {
        return especialidad;
    }




    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
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