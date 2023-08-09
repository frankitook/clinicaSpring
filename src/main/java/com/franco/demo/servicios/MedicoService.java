package com.franco.demo.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.franco.demo.dominio.Medico;

import com.franco.demo.interfazservicios.IMedicoService;
import com.franco.demo.repositorio.MedicoRepository;



@Service
public class MedicoService implements IMedicoService {


     @Autowired
    private MedicoRepository dao;

    @Override
    public Medico buscarMedicoPorEmailYCorreo(String email, String contrasena) {
        return (Medico)dao.findByEmailAndContrasena(email , contrasena);
    }

    @Override
    public Medico buscarMedicoPorEmail(String email) {
        return (Medico)dao.findByEmail(email);
    }

    @Override
    public void guardar(Medico u) {
        dao.save(u);
    }

    @Override
    public List<Medico> traeMedicos() {
        return dao.findAll();
    }

    @Override
    public Optional<Medico> buscarPorID(int id) {
        return dao.findById(id);
    }
    
}
