
package com.franco.demo.controlador;



import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.franco.demo.dominio.HorarioMedico;
import com.franco.demo.dominio.Medico;
import com.franco.demo.dominio.Paciente;
import com.franco.demo.dominio.Turno;
import com.franco.demo.interfazservicios.IHorarioMedicoService;
import com.franco.demo.interfazservicios.IMedicoService;
import com.franco.demo.interfazservicios.IPacienteService;
import com.franco.demo.interfazservicios.ITurnoService;




@Controller
@RequestMapping("/home")
public class Controlador {

    
     @Autowired
    private IPacienteService servicioPaciente;

    @Autowired
    private IHorarioMedicoService servicioHorario;

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

    @GetMapping("/obtenerHorarios")
    @ResponseBody
    public List<String> obtenerHorariosDisponibles(@RequestParam String fecha, @RequestParam String idMedico) throws ParseException {
    
        List<String> horariosMostrar = new ArrayList<>();
    
        String fechaTexto = fecha;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    
        java.util.Date utilDate = sdf.parse(fechaTexto);
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
    
        String diaa = obtenerDiaSemana(sqlDate);
    
        Optional<Medico> medico = servicioMedico.buscarPorID(Integer.parseInt(idMedico));
    
        List<HorarioMedico> horarios = servicioHorario.traeHorariosDeUnMedico(medico.get());
    
        HorarioMedico h = new HorarioMedico();
        for (HorarioMedico horario : horarios) {
            if (horario.getDia().equals(diaa)) {
                h = horario;
                break;
            }
        }
    
        java.sql.Time horaInicio = h.getHoraInicio();
        java.sql.Time horaFin = h.getHoraFin();
    
        horariosMostrar = generarHorarios(horaInicio, horaFin);
    
        List<Turno> horariosTurnos = servicio.traeTurnosDeUnMedicoEnUnaFecha(medico.get(), sqlDate);
    
        List<String> horariosDisponibles = new ArrayList<>(horariosMostrar);
    
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        for (Turno turno : horariosTurnos) {
            String horaFormateada = formatter.format(turno.getHoraAtencion());
            if (turno.getEstado().equals("Pendiente")) {
                horariosDisponibles.remove(horaFormateada);
            } else if (turno.getEstado().equals("Cancelado")) {
                if (!horariosDisponibles.contains(horaFormateada)) {
                    horariosDisponibles.add(horaFormateada);
                }
            }
        }
    
        // Ordenar la lista de horarios disponibles por hora
        horariosDisponibles.sort(String::compareTo);
    
        return horariosDisponibles;
    }
    

    @PostMapping("/actualizarHorario")
    public String guardarNuevoHorario(@RequestParam String dia, @RequestParam String horaInicio, @RequestParam String horaFin,
                                      @RequestParam String nuevaHoraInicio, @RequestParam String nuevaHoraFin) {
        // Pseudo-código para actualizar el horario en la base de datos
       
        // Convierte las horas a formatos adecuados (por ejemplo, LocalTime en Java 8+)
        Time horaInicioTime = Time.valueOf(horaInicio);
        Time horaFinTime = Time.valueOf(horaFin);
        LocalTime nuevaHoraInicioLocal = LocalTime.parse(nuevaHoraInicio);
        LocalTime nuevaHoraFinLocal = LocalTime.parse(nuevaHoraFin);
    
        // Lógica para obtener y actualizar el horario en la base de datos
        Optional<HorarioMedico> horario = servicioHorario.traeUnHorariodeUnMedico(usuarioMedico, dia);
    
        if (horario.isPresent()) {
            HorarioMedico h = horario.get();
            
            // Actualiza solo si los valores no son nulos
            if (nuevaHoraInicio != null && nuevaHoraFin != null) {
                Time nuevaHoraInicioTime = Time.valueOf(nuevaHoraInicioLocal);
                Time nuevaHoraFinTime = Time.valueOf(nuevaHoraFinLocal);
                
                h.setHoraInicio(nuevaHoraInicioTime);
                h.setHoraFin(nuevaHoraFinTime);
                
                servicioHorario.guardarHorarioMedico(h);
            }
        }
    
        // Redirecciona a la página adecuada
        return "redirect:/home/inicioMedico"; // Cambia la URL según tu configuración
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
    Map<String, Object> atributos = new HashMap<>();

    // Obtener la fecha de hoy
    LocalDate fechaHoy = LocalDate.now();

    // Filtrar y obtener los turnos de hoy con estado pendiente
    List<Turno> turnosHoyPendientesYFinalizados = servicio.traeTurnosDeUnMedico(usuarioMedico).stream()
    .filter(turno -> turno.getFechaAtencion().toLocalDate().isEqual(fechaHoy))
    .filter(turno -> turno.getEstado().equals("Pendiente") || turno.getEstado().equals("Finalizado"))
    .collect(Collectors.toList());


    // Obtener horarios
    List<HorarioMedico> horarios = servicioHorario.traeHorariosDeUnMedico(usuarioMedico);

    // Ordenar la lista de turnos por fecha y hora ascendente
    Collections.sort(turnosHoyPendientesYFinalizados,
            Comparator.comparing(Turno::getFechaAtencion)
                    .thenComparing(Turno::getHoraAtencion)
    );

    atributos.put("horarios", ordenarHorariosPorDia(horarios));
    atributos.put("turnos", turnosHoyPendientesYFinalizados);

    model.addAllAttributes(atributos);

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


   

    @PostMapping("/modificar")
    public String modificarHorarioMedico(
        @RequestParam("dia") String diaModificado,
        @RequestParam("horaInicio") Time horaInicio,
        @RequestParam("horaFin") Time horaFin,
        Model model) {
        
        model.addAttribute("diaModificado", diaModificado);
        model.addAttribute("horaInicio", horaInicio);
        model.addAttribute("horaFin", horaFin);
        return "modificarTurnoMedico";
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
    

     public  String obtenerDiaSemana(Date fecha) {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(fecha);

            int diaSemana = calendar.get(Calendar.DAY_OF_WEEK);

            String[] nombresDias = {"Domingo", "Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado"};
            return nombresDias[diaSemana - 1];

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

   public static List<String> generarHorarios(java.sql.Time horaInicio, java.sql.Time horaFin) {
    List<String> horarios = new ArrayList<>();
    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

    try {
        Calendar calInicio = Calendar.getInstance();
        calInicio.setTime(horaInicio);

        Calendar calFin = Calendar.getInstance();
        calFin.setTime(horaFin);

        while (calInicio.before(calFin)) {
            horarios.add(sdf.format(calInicio.getTime()));

            calInicio = (Calendar) calInicio.clone();
            calInicio.add(Calendar.MINUTE, 45);
        }

        horarios.add(sdf.format(calFin.getTime()));

    } catch (Exception e) {
        e.printStackTrace();
    }

    return horarios;
}

public  List<HorarioMedico> ordenarHorariosPorDia(List<HorarioMedico> horarios) {

    String[] ordenDias = {"Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado", "Domingo"};
    
    List<HorarioMedico> horariosOrdenados= new ArrayList<>();

    for(int i=0; i<ordenDias.length; i++) {

        for(int j=0;j<horarios.size();j++) {

            if(ordenDias[i].equals(horarios.get(j).getDia())){

                horariosOrdenados.add(horarios.get(j));

            }

        }


    }
    return horariosOrdenados;
}

    
}
