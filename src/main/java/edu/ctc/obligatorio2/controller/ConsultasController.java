package edu.ctc.obligatorio2.controller;

import java.util.List;

import edu.ctc.obligatorio2.entity.Chofer;
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
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/consultas")
public class ConsultasController {
	
	private final TurnoServicio turnoServicio;
	
	public ConsultasController(TurnoServicio turnoServicio) {
        this.turnoServicio = turnoServicio;
    }


	//home consultas
	@GetMapping({ "/", "" })
	public String pagListaConsultas(@RequestParam(value = "id", required = false) String participant, Model modelo) {
		return "homeConsultas.html";
	}

	//Consulta 1
	@GetMapping({"/consulta1/?id={id}"})
    public String cochesParaUnTurnow(@PathVariable Long id, Model modelo){
		Turno turno = turnoServicio.findTurnoById(id);
		List<Coche> coches = turno.getListaCoches();
    	//List<Coche> coches = (List<Coche>) ResponseEntity.status(HttpStatus.OK).body(turnoServicio.cochesParaUnTurno(id));
        modelo.addAttribute("consultas", coches);
    	return "consulta1.html";
    }

}
