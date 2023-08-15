package com.franco.demo.interfazservicios;

import java.util.List;
import java.util.Optional;

import com.franco.demo.dominio.HorarioMedico;
import com.franco.demo.dominio.Medico;

public interface IHorarioMedicoService {
    

    public List<HorarioMedico> traeHorariosDeUnMedico(Medico medico);

    public Optional<HorarioMedico> traeUnHorariodeUnMedico(Medico medico, String dia);

    public void guardarHorarioMedico(HorarioMedico horarioMedico);
}
