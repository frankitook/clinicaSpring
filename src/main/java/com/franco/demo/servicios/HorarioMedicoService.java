package com.franco.demo.servicios;

import java.util.List;

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

    


    
}
