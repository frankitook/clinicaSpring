package com.franco.demo.interfazservicios;


import java.util.Optional;

import com.franco.demo.dominio.Paciente;


public interface IPacienteService {
    
    public Paciente buscarPacientePorEmailYCorreo(String email, String contrasena);

    public Paciente buscarPorEmail(String email);

    public void guardar(Paciente u);


    public Optional<Paciente> buscarPacientePorID(int id);

}
