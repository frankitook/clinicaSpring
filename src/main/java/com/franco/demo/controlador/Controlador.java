
package com.franco.demo.controlador;



import java.sql.Date;
import java.sql.Time;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Collections;
import java.util.Comparator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.franco.demo.dominio.Medico;
import com.franco.demo.dominio.Paciente;
import com.franco.demo.dominio.Turno;

import com.franco.demo.interfazservicios.IMedicoService;
import com.franco.demo.interfazservicios.IPacienteService;
import com.franco.demo.interfazservicios.ITurnoService;







@Controller
@RequestMapping("/home")
public class Controlador {

    
     @Autowired
    private IPacienteService servicioPaciente;

     @Autowired
    private IMedicoService servicioMedico;

     @Autowired
     private ITurnoService servicio;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    Paciente usuarioPaciente= new Paciente();
    Medico usuarioMedico= new Medico();

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

    Turno turno = new Turno(usuarioPaciente, servicioMedico.buscarPorID(medicoId).get(), fechaAtencion, Time.valueOf(horaAtencion), "Pendiente");
    servicio.guardar(turno);

      
    return "redirect:/home/inicioPaciente"; 
}

@PostMapping("/cancelarTurno/{id}")
public String cancelarTurno(@PathVariable String id) {
    Optional<Turno> turno = servicio.traeUnTurno(Integer.parseInt(id));
    if (turno.isPresent()) {
        Turno t = turno.get();
        t.setEstado("Cancelado");
        servicio.guardar(t);
    }
    return "redirect:/home/inicioPaciente";
}


@PostMapping("/finalizarTurno/{id}")
public String finalizarTurno(@PathVariable String id) {
    Optional<Turno> turno = servicio.traeUnTurno(Integer.parseInt(id));
    if (turno.isPresent()) {
        Turno t = turno.get();
        t.setEstado("Finalizado");
        servicio.guardar(t);
    }
    return "redirect:/home/inicioMedico";
}

@GetMapping("/inicioMedico")
public String inicioMedico(Model model) {
    List<Turno> turnos = servicio.traeTurnosDeUnMedico(usuarioMedico);

    // Ordenar la lista de turnos por fecha y hora ascendente
    Collections.sort(turnos, 
        Comparator.comparing(Turno::getFechaAtencion)
                  .thenComparing(Turno::getHoraAtencion)
    );

    model.addAttribute("turnos", turnos);
    return "medico";
}


    @GetMapping("/inicioPaciente")
    public String inicioPaciente(Model model) {
        Map<String, Object> atributos = new HashMap<>();
       
        List<Turno> turnos = servicio.traeTurnosDeUnPaciente(usuarioPaciente);
        List<Medico> listaMedicos= servicioMedico.traeMedicos();

        Collections.sort(turnos, 
        Comparator.comparing(Turno::getFechaAtencion)
                  .thenComparing(Turno::getHoraAtencion)
    );
        atributos.put("medicos", listaMedicos);
        atributos.put("turnos", turnos);
        

        model.addAllAttributes(atributos);
        return "paciente";
    }

  

    @PostMapping("/ingresar")
    public String ingresar(@RequestParam("correo") String correo, @RequestParam("contrasena") String contrasena, Model model) {
        // Buscar el usuario en la base de datos por correo
        Medico usuarioM = servicioMedico.buscarMedicoPorEmail(correo);
            
        if (usuarioM != null && passwordEncoder.matches(contrasena, usuarioM.getContrasena())) {
           
                usuarioMedico= usuarioM;
                return "redirect:/home/inicioMedico";
            
            
        } else {

            Paciente usuarioP = servicioPaciente.buscarPorEmail(correo);

            if (usuarioP != null && passwordEncoder.matches(contrasena, usuarioP.getContrasena())) {
                usuarioPaciente=usuarioP;
                
                return "redirect:/home/inicioPaciente";
            }

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

        
        if(tipo.equals("Paciente")){

            Paciente paciente = new Paciente(Integer.parseInt(dni), convertirPrimeraLetraMayuscula(nombre),convertirPrimeraLetraMayuscula( apellido), correo, passwordEncoder.encode(contrasena));
            servicioPaciente.guardar(paciente);
            
        } else{

            Medico medico = new Medico(Integer.parseInt(dni), convertirPrimeraLetraMayuscula(nombre),convertirPrimeraLetraMayuscula( apellido), correo, passwordEncoder.encode(contrasena));
            servicioMedico.guardar(medico);

        }                           
        
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
