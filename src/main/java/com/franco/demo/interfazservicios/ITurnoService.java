package com.franco.demo.interfazservicios;


import java.sql.Date;
import java.util.List;
import java.util.Optional;

import com.franco.demo.dominio.Medico;
import com.franco.demo.dominio.Paciente;
import com.franco.demo.dominio.Turno;




public interface ITurnoService {

    public List<Turno> traeTurnos();

    public void guardar(Turno t);

     public List<Turno> traeTurnosDeUnMedico(Medico medico);

     public List<Turno> traeTurnosDeUnPaciente(Paciente paciente);

     public Optional<Turno> traeUnTurno(int id);

     public List<Turno> traeTurnosDeUnMedicoEnUnaFecha(Medico medico, Date fecha);

     

}
    

