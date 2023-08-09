package com.franco.demo.interfazservicios;

import java.util.List;
import java.util.Optional;

import com.franco.demo.dominio.Medico;


public interface IMedicoService {
    
    public Medico buscarMedicoPorEmailYCorreo(String email, String contrasena);

    public Medico buscarMedicoPorEmail(String email);

    public void guardar(Medico u);

    public List<Medico> traeMedicos();


    public Optional<Medico> buscarPorID(int id);

}
