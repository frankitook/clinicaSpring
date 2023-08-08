package com.franco.demo.interfazservicios;

import java.util.List;
import java.util.Optional;

import com.franco.demo.dominio.Usuario;

public interface IUsuarioService {
    public Usuario buscarPorEmailYCorreo(String email, String contrasena);

    public Usuario buscarPorEmail(String email);

    public void guardar(Usuario u);

    public List<Usuario> traeMedicos();


    public Optional<Usuario> buscarPorID(int id);

}