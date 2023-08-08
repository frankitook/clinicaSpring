package com.franco.demo.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.franco.demo.dominio.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    
    Usuario findByEmailAndContrasena(String email, String contrasena);

    Usuario findByEmail(String email);

    @Query("SELECT u FROM Usuario u WHERE u.tipo = 'Medico'")
    List<Usuario> findAllMedicos();
}
