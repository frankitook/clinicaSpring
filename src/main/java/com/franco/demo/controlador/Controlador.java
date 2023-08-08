
package com.franco.demo.controlador;



import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.franco.demo.dominio.Turno;
import com.franco.demo.dominio.Usuario;
import com.franco.demo.interfazservicios.ITurnoService;
import com.franco.demo.interfazservicios.IUsuarioService;






@Controller
@RequestMapping("/home")
public class Controlador {

     @Autowired
    private IUsuarioService service;

     @Autowired
     private ITurnoService servicio;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    Usuario usuarioPaciente= new Usuario();

    @GetMapping("/saludo")
    public String saludar(){
       
    return "index";
    }

    @GetMapping("/registro")
    public String mostrarFormularioRegistro() {
        return "nuevoUsuario";
    }

    @PostMapping("/registrarTurno")
    public String guardarTurno(@RequestParam("medicoId") int medicoId,
                               @RequestParam("fechaAtencion") Date fechaAtencion,
                               @RequestParam("horaAtencion") String horaAtencionStr, 
                               Model model) {


    LocalTime horaAtencion = LocalTime.parse(horaAtencionStr);

    Turno turno = new Turno(usuarioPaciente, service.buscarPorID(medicoId).get(), fechaAtencion, Time.valueOf(horaAtencion), "Pendiente");
    servicio.guardar(turno);

      
    return "redirect:/home/inicioPaciente"; 
}



      @GetMapping("/inicioPaciente")
    public String inicioPaciente(Model model) {
        
        List<Usuario> listaMedicos= service.traeMedicos();
        List<Turno> turnos = servicio.traeTurnosDeUnMedico(usuarioPaciente);
        model.addAttribute("turnos", turnos);
        model.addAttribute("medicos", listaMedicos);
  
        return "paciente";
    }

       @GetMapping("/inicioMedico")
    public String inicioMedico(Model model) {
        List<Turno> turnos = servicio.traeTurnosDeUnMedico(usuarioPaciente);
        model.addAttribute("turnos", turnos);
        return "medico";
    }

  

    @PostMapping("/ingresar")
    public String ingresar(@RequestParam("correo") String correo, @RequestParam("contrasena") String contrasena, Model model) {
        // Buscar el usuario en la base de datos por correo
        Usuario usuario = service.buscarPorEmail(correo);
            usuarioPaciente = usuario;
        if (usuario != null && passwordEncoder.matches(contrasena, usuario.getContrasena())) {
           
            if(usuario.getTipo().equals("Paciente")){
                
            return "redirect:/home/inicioPaciente";
            }else{

                
                return "redirect:/home/inicioMedico";
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
