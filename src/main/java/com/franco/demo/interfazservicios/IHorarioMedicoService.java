package com.franco.demo.interfazservicios;

import java.util.List;

import com.franco.demo.dominio.HorarioMedico;
import com.franco.demo.dominio.Medico;

public interface IHorarioMedicoService {
    

    public List<HorarioMedico> traeHorariosDeUnMedico(Medico medico);
}
