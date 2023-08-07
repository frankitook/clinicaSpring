package com.franco.demo.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.franco.demo.dominio.Usuario;
import com.franco.demo.interfazservicios.IUsuarioService;
import com.franco.demo.repositorio.UsuarioRepository;

@Service
public class UsuarioService implements IUsuarioService{
    

    @Autowired
    private UsuarioRepository dao;
    

    @Override
    public Usuario buscarPorEmailYCorreo(String correo, String contrasena) {
        
        return (Usuario)dao.findByEmailAndContrasena(correo , contrasena);
    }
 
    

    @Override
    public Usuario buscarPorEmail(String correo) {
        
        return (Usuario)dao.findByEmail(correo);
    }



    @Override
    public void guardar(Usuario u) {
        dao.save(u);
    }
    
    
}
