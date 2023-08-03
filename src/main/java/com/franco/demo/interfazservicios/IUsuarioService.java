package com.franco.demo.interfazservicios;

import com.franco.demo.dominio.Usuario;

public interface IUsuarioService {
    public Usuario buscarPorEmailYCorreo(String email, String contrasena);

    public Usuario buscarPorEmail(String email);

    public void guardar(Usuario u);

}