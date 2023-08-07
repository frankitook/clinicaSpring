package com.franco.demo.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


    
    
}
