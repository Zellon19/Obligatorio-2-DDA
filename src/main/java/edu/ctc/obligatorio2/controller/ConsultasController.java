package edu.ctc.obligatorio2.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.ctc.obligatorio2.entity.Coche;
import edu.ctc.obligatorio2.entity.Turno;
import edu.ctc.obligatorio2.service.TurnoServicio;

@Controller
@RequestMapping("/consultas")
public class ConsultasController {
	
	private final TurnoServicio turnoServicio;
	
	public ConsultasController(TurnoServicio turnoServicio) {
        this.turnoServicio = turnoServicio;
    }
	
	
	//Consulta 1
	@GetMapping({"/{id}", ""})
    public String cochesParaUnTurnow(@PathVariable("id") Long id, Model modelo){
		Turno turno = turnoServicio.findTurnoById(id);
		List<Coche> coches = turno.getListaCoches();
    	//List<Coche> coches = (List<Coche>) ResponseEntity.status(HttpStatus.OK).body(turnoServicio.cochesParaUnTurno(id));
        modelo.addAttribute("consultas", coches);
    	return "consultas.html";
    }

}
