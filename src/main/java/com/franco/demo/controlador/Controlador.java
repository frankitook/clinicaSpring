
package com.franco.demo.controlador;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.franco.demo.dominio.Usuario;
import com.franco.demo.repositorio.UsuarioRepository;



@Controller
@RequestMapping("/home")
public class Controlador {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    

    @GetMapping("/saludo")
    public String saludar(){
       
    return "inicio";
    }

    @GetMapping("/registro")
    public String mostrarFormularioRegistro() {
        return "nuevoUsuario";
    }


    @PostMapping("/ingresar")
    public String ingresar(@RequestParam("correo") String correo, @RequestParam("contrasena") String contrasena, Model model) {
        // Buscar el usuario en la base de datos por correo
        Usuario usuario = usuarioRepository.findByEmail(correo);
    
        if (usuario != null && passwordEncoder.matches(contrasena, usuario.getContrasena())) {
            model.addAttribute("mensaje", "Hola " + correo + ", has iniciado sesión correctamente.");
            return "bienvenida";
        } else {
            model.addAttribute("error", "Usuario o contraseña inválida. Inténtalo de nuevo.");
            return "inicio";
        }
    }
    

    @PostMapping("/registrar")
    public String procesarRegistro(@RequestParam("correo") String correo, @RequestParam("contrasena") String contrasena,
            Model model) {

        
        Usuario nuevoUsuario = new Usuario(1,correo, passwordEncoder.encode(contrasena));

        // Guardar el nuevo usuario en la base de datos
        usuarioRepository.save(nuevoUsuario);

        return "redirect:/home/saludo";
    }



    

    
}
