package com.franco.demo.servicios;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.franco.demo.dominio.Medico;
import com.franco.demo.dominio.Paciente;
import com.franco.demo.dominio.Turno;

import com.franco.demo.interfazservicios.ITurnoService;
import com.franco.demo.repositorio.TurnoRepository;


@Service
public class TurnoService implements ITurnoService {


    @Autowired
    private TurnoRepository dao;

    @Override
    public List<Turno> traeTurnos() {
        return dao.findAll();
    }

    @Override
    public void guardar(Turno t) {
        dao.save(t);
    }

    @Override
    public List<Turno> traeTurnosDeUnMedico(Medico medico) {
       return dao.findByMedico(medico);
    }

    @Override
    public List<Turno> traeTurnosDeUnPaciente(Paciente paciente) {
       return dao.findByPaciente(paciente);
    }

    @Override
    public Optional<Turno> traeUnTurno(int id) {
        return dao.findById(id);
    }

   

   

    


    
    
}
