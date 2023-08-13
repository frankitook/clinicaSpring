package com.franco.demo.repositorio;



import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.franco.demo.dominio.Medico;
import com.franco.demo.dominio.Paciente;
import com.franco.demo.dominio.Turno;


public interface TurnoRepository extends JpaRepository<Turno, Integer> {

   @Query("SELECT t FROM Turno t WHERE t.medico = ?1")
    List<Turno> findByMedico(Medico medico);   

    @Query("SELECT t FROM Turno t WHERE t.paciente = ?1")
    List<Turno> findByPaciente(Paciente paciente);

    List<Turno> findTurnosByMedicoAndFechaAtencion(Medico medico, Date fechaAtencion);

    
  
}
