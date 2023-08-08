package com.franco.demo.servicios;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.franco.demo.dominio.Turno;
import com.franco.demo.dominio.Usuario;
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
    public List<Turno> traeTurnosDeUnMedico(Usuario medico) {
       return dao.findByMedico(medico);
    }

    @Override
    public List<Turno> traeTurnosDeUnPaciente(Usuario paciente) {
       return dao.findByPaciente(paciente);
    }

   

    


    
    
}
