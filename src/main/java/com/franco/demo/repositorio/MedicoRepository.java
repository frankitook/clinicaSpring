package com.franco.demo.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.franco.demo.dominio.Medico;



public interface MedicoRepository extends JpaRepository<Medico, Integer> {
    
    Medico findByEmailAndContrasena(String email, String contrasena);

    Medico findByEmail(String email);

}
