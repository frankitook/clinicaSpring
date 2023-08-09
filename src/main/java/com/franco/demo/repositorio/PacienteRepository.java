package com.franco.demo.repositorio;

import com.franco.demo.dominio.Paciente;


import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Integer> {

    Paciente findByEmailAndContrasena(String email, String contrasena);

    Paciente findByEmail(String email);
    
}
