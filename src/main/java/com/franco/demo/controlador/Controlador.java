
package com.franco.demo.controlador;



import org.springframework.beans.factory.annotation.Autowired;
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
        // Buscar el usuario en la base de datos por correo y contraseña
        Usuario usuario = usuarioRepository.findByEmailAndContrasena(correo, contrasena);

        if (usuario != null) {
            model.addAttribute("mensaje", "Hola " + correo + ", tu contraseña es: " + contrasena);
            return "bienvenida";
        } else {
            model.addAttribute("error", "Usuario o contraseña inválida. Inténtalo de nuevo.");
            return "inicio";
        }
    }
    

    @PostMapping("/registrar")
    public String procesarRegistro(@RequestParam("correo") String correo, @RequestParam("contrasena") String contrasena,
            Model model) {

        
        Usuario nuevoUsuario = new Usuario(1,correo, contrasena);

        // Guardar el nuevo usuario en la base de datos
        usuarioRepository.save(nuevoUsuario);

        return "redirect:/home/saludo";
    }



    

    
}
