package com.franco.demo.servicios;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.franco.demo.dominio.Paciente;
import com.franco.demo.interfazservicios.IPacienteService;
import com.franco.demo.repositorio.PacienteRepository;


@Service
public class PacienteService implements IPacienteService{

    @Autowired
    private PacienteRepository dao;

    @Override
    public Paciente buscarPacientePorEmailYCorreo(String email, String contrasena) {
       return (Paciente)dao.findByEmailAndContrasena(email , contrasena);
    }

    @Override
    public Paciente buscarPorEmail(String email) {
        return (Paciente)dao.findByEmail(email);
    }

    @Override
    public void guardar(Paciente u) {
        dao.save(u);
    }

    @Override
    public Optional<Paciente> buscarPacientePorID(int id) {
        return dao.findById(id);
    }
    
}
