package edu.ctc.obligatorio2.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.ctc.obligatorio2.entity.Coche;
import edu.ctc.obligatorio2.entity.Turno;
import edu.ctc.obligatorio2.entity.Viaje;
import edu.ctc.obligatorio2.service.TurnoServicio;
import edu.ctc.obligatorio2.service.ViajeServicio;

@Controller
@RequestMapping("/consultas")
public class ConsultasController {
	
	private final TurnoServicio turnoServicio;
	private final ViajeServicio viajeServicio;
	
	public ConsultasController(TurnoServicio turnoServicio, ViajeServicio viajeServicio) {
        this.turnoServicio = turnoServicio;
        this.viajeServicio = viajeServicio;
    }


	//home consultas
	@GetMapping({ "/", "" })
	public String pagListaConsultas(String participant, Model modelo) {
		return "homeConsultas.html";
	}

	//Consulta 1
    @GetMapping({"/consulta1"})
    public String cochesParaUnTurnow(@RequestParam(value="id",required=true) Long id, Model modelo){
        Turno turno = turnoServicio.findTurnoById(id);
        List<Coche> coches = turno.getListaCoches();
        modelo.addAttribute("consultas", coches);
        return "consulta1.html";
    }
    
    
    //Consulta 2
    @GetMapping({"/consulta2"})
    public String viajesPorChoferPorFecha(@RequestParam(value="idChofer",required=true) Long id, @RequestParam(value="fecha",required=false) LocalDateTime fecha, @RequestParam(value="fecha2",required=false) LocalDateTime fecha2, Model modelo){
        List<Viaje> todosLosViajes = viajeServicio.findAllViajes();
        List<Viaje> retorno = todosLosViajes;
        retorno.clear();
        for(Viaje viaje: todosLosViajes) {
        	if(fecha2 == null) {
        		if(viaje.getChofer().getId() == id && viaje.getFechaHora().toLocalDate().equals(fecha.toLocalDate())) {
            		retorno.add(viaje);
            	}
        	}
        	else if(fecha != null && fecha2 != null) {
        		if(viaje.getChofer().getId() == id && (viaje.getFechaHora().toLocalDate().isAfter(fecha.toLocalDate()) || viaje.getFechaHora().toLocalDate().equals(fecha.toLocalDate()) || viaje.getFechaHora().toLocalDate().isBefore(fecha2.toLocalDate()) || viaje.getFechaHora().toLocalDate().equals(fecha2.toLocalDate()))) {
            		retorno.add(viaje);
            	}
        	}
        }
        modelo.addAttribute("consultas", retorno);
        return "consulta2.html";
    }
	
    
    //Consulta 3
    @GetMapping({"/consulta3"})
    public String recaudadoPorChoferPorViajeTurnoFecha(@RequestParam(value="idChofer",required=true) Long idChofer, @RequestParam(value="idViaje",required=false) Long idViaje, @RequestParam(value="idTurno",required=false) Long idTurno, @RequestParam(value="fecha",required=false) String fecha, Model modelo) throws ParseException{
        List<Viaje> todosLosViajes = viajeServicio.findAllViajes();
        Float total = 0f;
        
        LocalDate fFecha = new SimpleDateFormat("yyyy/MM/dd").parse(fecha).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        for(Viaje viaje: todosLosViajes) {
        	if(idViaje != null) {
        		if(viaje.getChofer().getId() == idChofer && viaje.getId() == idViaje) {
            		total += viaje.getPrecio();
            	}
        	}
        	else if(idTurno != null) {
        		if(viaje.getChofer().getId() == idChofer && viaje.getTurno().getId() == idTurno) {
            		total += viaje.getPrecio();
            	}
        	}
        	else if(fecha != null) {
        		if(viaje.getChofer().getId() == idChofer && viaje.getFechaHora().toLocalDate().equals(fFecha)) {
            		total += viaje.getPrecio();
            	}
        	}
        }
        modelo.addAttribute("consultas", total);
        return "consulta3.html";
    }
    
    
    
    //Consulta 4
    @GetMapping({"/consulta4"})
    public String kmRecorridosPorCochePorDiaMesChofer(@RequestParam(value="idCoche",required=true) Long idCoche, @RequestParam(value="dia",required=false) LocalDateTime dia, @RequestParam(value="mes",required=false) LocalDateTime mes, @RequestParam(value="idChofer",required=false) Long idChofer, Model modelo){
        List<Viaje> todosLosViajes = viajeServicio.findAllViajes();
        Float total = 0f;
        for(Viaje viaje: todosLosViajes) {
        	if(dia != null) {
        		if(viaje.getCoche().getId() == idCoche && viaje.getFechaHora().toLocalDate().equals(dia.toLocalDate())) {
            		total += viaje.getKmRecorridos();
            	}
        	}
        	else if(mes != null) {
        		if(viaje.getCoche().getId() == idCoche && (viaje.getFechaHora().getMonth().equals(mes.getMonth()) && viaje.getFechaHora().getYear() == mes.getYear())) {
            		total += viaje.getKmRecorridos();
            	}
        	}
        	else if(idChofer != null) {
        		if(viaje.getCoche().getId() == idCoche && viaje.getChofer().getId() == idChofer) {
        			total += viaje.getKmRecorridos();
            	}
        	}
        }
        modelo.addAttribute("consultas", total);
        return "consulta4.html";
    }

}
