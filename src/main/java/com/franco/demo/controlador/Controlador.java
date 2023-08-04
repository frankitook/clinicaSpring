
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
import com.franco.demo.interfazservicios.IUsuarioService;






@Controller
@RequestMapping("/home")
public class Controlador {

     @Autowired
    private IUsuarioService service;

    @Autowired
    private PasswordEncoder passwordEncoder;
    

    @GetMapping("/saludo")
    public String saludar(){
       
    return "index";
    }

    @GetMapping("/registro")
    public String mostrarFormularioRegistro() {
        return "nuevoUsuario";
    }


    @PostMapping("/ingresar")
    public String ingresar(@RequestParam("correo") String correo, @RequestParam("contrasena") String contrasena, Model model) {
        // Buscar el usuario en la base de datos por correo
        Usuario usuario = service.buscarPorEmail(correo);
    
        if (usuario != null && passwordEncoder.matches(contrasena, usuario.getContrasena())) {
            model.addAttribute("mensaje", "Hola " + correo + ", has iniciado sesión correctamente.");
            return "bienvenida";
        } else {
            model.addAttribute("error", "Usuario o contraseña inválida. Inténtalo de nuevo.");
            return "index";
        }
    }
    

    @PostMapping("/registrar")
    public String procesarRegistro(@RequestParam("id") String id,@RequestParam("correo") String correo, @RequestParam("contrasena") String contrasena,
            Model model) {

        
        Usuario nuevoUsuario = new Usuario(Integer.parseInt(id) ,correo, passwordEncoder.encode(contrasena));

        // Guardar el nuevo usuario en la base de datos
        service.guardar(nuevoUsuario);

        return "redirect:/home/saludo";
    }

    @GetMapping("/cerrar")
    public String cerrarSesion(){

        return "redirect:/home/saludo";

    }


    

    
}
