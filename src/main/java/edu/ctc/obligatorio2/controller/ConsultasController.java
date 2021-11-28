package edu.ctc.obligatorio2.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
    public String viajesPorChoferPorFecha(@RequestParam(value="idChofer",required=true) Long id, @RequestParam(value="fecha",required=true) LocalDateTime fecha, Model modelo){
        List<Viaje> todosLosViajes = viajeServicio.findAllViajes();
        List<Viaje> retorno = todosLosViajes;
        retorno.clear();
        for(Viaje viaje: todosLosViajes) {
        	if(viaje.getChofer().getId() == id && viaje.getFechaHora().toLocalDate().equals(fecha.toLocalDate())) {
        		retorno.add(viaje);
        	}
        }
        modelo.addAttribute("consultas", retorno);
        return "consulta2.html";
    }
	
    @GetMapping({"/consulta2"})
    public String viajesPorChoferPorFecha(@RequestParam(value="idChofer",required=true) Long id, @RequestParam(value="fecha1",required=true) LocalDateTime fecha1, @RequestParam(value="fecha2",required=true) LocalDateTime fecha2, Model modelo){
        List<Viaje> todosLosViajes = viajeServicio.findAllViajes();
        List<Viaje> retorno = todosLosViajes;
        retorno.clear();
        for(Viaje viaje: todosLosViajes) {
        	if(viaje.getChofer().getId() == id && (viaje.getFechaHora().toLocalDate().isAfter(fecha1.toLocalDate()) || viaje.getFechaHora().toLocalDate().isBefore(fecha2.toLocalDate()))) {
        		retorno.add(viaje);
        	}
        }
        modelo.addAttribute("consultas", retorno);
        return "consulta2.html";
    }
    
    //Consulta 3
    @GetMapping({"/consulta3"})
    public String recaudadoPorChoferPorViaje(@RequestParam(value="idChofer",required=true) Long idChofer, @RequestParam(value="idViaje",required=true) Long idViaje, Model modelo){
        List<Viaje> todosLosViajes = viajeServicio.findAllViajes();
        Float total = 0f;
        for(Viaje viaje: todosLosViajes) {
        	if(viaje.getChofer().getId() == idChofer && viaje.getId() == idViaje) {
        		total += viaje.getPrecio();
        	}
        }
        modelo.addAttribute("consultas", total);
        return "consulta3.html";
    }
    
    @GetMapping({"/consulta3"})
    public String recaudadoPorChoferPorTurno(@RequestParam(value="idChofer",required=true) Long idChofer, @RequestParam(value="idTurno",required=true) Long idTurno, Model modelo){
        List<Viaje> todosLosViajes = viajeServicio.findAllViajes();
        Float total = 0f;
        for(Viaje viaje: todosLosViajes) {
        	if(viaje.getChofer().getId() == idChofer && viaje.getTurno().getId() == idTurno) {
        		total += viaje.getPrecio();
        	}
        }
        modelo.addAttribute("consultas", total);
        return "consulta3.html";
    }
    
    @GetMapping({"/consulta3"})
    public String recaudadoPorChoferPorFecha(@RequestParam(value="idChofer",required=true) Long idChofer, @RequestParam(value="fecha",required=true) LocalDateTime fecha, Model modelo){
        List<Viaje> todosLosViajes = viajeServicio.findAllViajes();
        Float total = 0f;
        for(Viaje viaje: todosLosViajes) {
        	if(viaje.getChofer().getId() == idChofer && viaje.getFechaHora().toLocalDate().equals(fecha.toLocalDate())) {
        		total += viaje.getPrecio();
        	}
        }
        modelo.addAttribute("consultas", total);
        return "consulta3.html";
    }

}
