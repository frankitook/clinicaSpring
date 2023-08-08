package com.franco.demo.interfazservicios;

import java.util.List;


import com.franco.demo.dominio.Turno;
import com.franco.demo.dominio.Usuario;



public interface ITurnoService {

    public List<Turno> traeTurnos();

    public void guardar(Turno t);

     public List<Turno> traeTurnosDeUnMedico(Usuario medico);

     public List<Turno> traeTurnosDeUnPaciente(Usuario paciente);

}
    

