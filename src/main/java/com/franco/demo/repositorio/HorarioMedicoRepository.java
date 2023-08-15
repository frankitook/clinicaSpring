package com.franco.demo.repositorio;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.franco.demo.dominio.HorarioMedico;
import com.franco.demo.dominio.Medico;


public interface HorarioMedicoRepository extends JpaRepository<HorarioMedico, Integer>{

    List<HorarioMedico> findByMedico(Medico medico);

    Optional<HorarioMedico> findByMedicoAndDia(Medico medico, String dia);
    
}
