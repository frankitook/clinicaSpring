package com.franco.demo.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.franco.demo.dominio.Turno;
import com.franco.demo.dominio.Usuario;


public interface TurnoRepository extends JpaRepository<Turno, Integer> {

   
    List<Turno> findByMedico(Usuario medico);   

    @Query("SELECT t FROM Turno t WHERE t.paciente = ?1")
    List<Turno> findByPaciente(Usuario paciente);
  
}
