
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
           
            if(usuario.getTipo().equals("Paciente")){
            return "paciente";
            }else{

                return "medico";
            }

            
        } else {
            model.addAttribute("error", "Usuario o contraseña inválida. Inténtalo de nuevo.");
            return "index";
        }
    }
    

    @PostMapping("/registrar")
    public String procesarRegistro(@RequestParam("dni") String dni,
                                   @RequestParam("nombre") String nombre, 
                                   @RequestParam("apellido") String apellido, 
                                   @RequestParam("correo") String correo, 
                                   @RequestParam("contrasena") String contrasena, 
                                   @RequestParam("tipo") String tipo,
                                   Model model) {

        
        Usuario nuevoUsuario = new Usuario(Integer.parseInt(dni),convertirPrimeraLetraMayuscula(nombre),convertirPrimeraLetraMayuscula(apellido),correo, passwordEncoder.encode(contrasena),tipo);

        // Guardar el nuevo usuario en la base de datos
        service.guardar(nuevoUsuario);

        return "redirect:/home/saludo";
    }

    @GetMapping("/cerrar")
    public String cerrarSesion(){

        return "redirect:/home/saludo";

    }

    public String convertirPrimeraLetraMayuscula(String palabra) {
        if (palabra == null || palabra.isEmpty()) {
            return palabra;
        }
    
        // Obtener la primera letra y convertirla a mayúscula
        String primeraLetra = palabra.substring(0, 1).toUpperCase();
    
        // Obtener el resto de la cadena y convertirla a minúsculas
        String restoPalabra = palabra.substring(1).toLowerCase();
    
        // Concatenar la primera letra mayúscula con el resto de la cadena en minúsculas
        return primeraLetra + restoPalabra;
    }
    

    

    
}
