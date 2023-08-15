package com.franco.demo.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.franco.demo.dominio.HorarioMedico;
import com.franco.demo.dominio.Medico;
import com.franco.demo.interfazservicios.IHorarioMedicoService;
import com.franco.demo.repositorio.HorarioMedicoRepository;

@Service
public class HorarioMedicoService implements IHorarioMedicoService {


    @Autowired
    private HorarioMedicoRepository dao;

    @Override
    public List<HorarioMedico> traeHorariosDeUnMedico(Medico medico) {
        return dao.findByMedico(medico);
    }

    @Override
    public Optional<HorarioMedico> traeUnHorariodeUnMedico(Medico medico, String dia) {
       return dao.findByMedicoAndDia(medico, dia);
    }

    @Override
    public void guardarHorarioMedico(HorarioMedico horarioMedico) {
         dao.save(horarioMedico);
    }

    


    
}
