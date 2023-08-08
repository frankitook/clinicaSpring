package com.franco.demo.interfazservicios;


import java.util.List;
import java.util.Optional;

import com.franco.demo.dominio.Turno;
import com.franco.demo.dominio.Usuario;



public interface ITurnoService {

    public List<Turno> traeTurnos();

    public void guardar(Turno t);

     public List<Turno> traeTurnosDeUnMedico(Usuario medico);

     public List<Turno> traeTurnosDeUnPaciente(Usuario paciente);

     public Optional<Turno> traeUnTurno(int id);

     

}
    

