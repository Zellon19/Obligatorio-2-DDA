package edu.ctc.obligatorio2.controller;

import edu.ctc.obligatorio2.entity.Coche;
import edu.ctc.obligatorio2.entity.Turno;
import edu.ctc.obligatorio2.repository.TurnoRepo;
import edu.ctc.obligatorio2.service.TurnoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/turno")
public class TurnoController {
    private final TurnoServicio turnoServicio;

    @Autowired
    TurnoRepo turnoRepo;
    
    public TurnoController(TurnoServicio turnoServicio) {
        this.turnoServicio = turnoServicio;
    }
    
    @GetMapping("/home")
    public String mostrarHome() {
        return "home";
    }

    //devuelve el http response con todos los turnos
    @GetMapping({"/turnos", ""})
    public String pagListaTurnos(Model modelo) {
    	List<Turno> turnos = turnoServicio.findAllTurnos();
    	modelo.addAttribute("turno", turnos);
    	return "";
    }

    // agrega un turno
    @GetMapping("/nuevoTurno")
    public String mostrarFormularioAgregarTurno(Model modelo) {
    	modelo.addAttribute("turno", new Turno());
    	return "nuevoTurno";
    }

    @PostMapping("/nuevoTurno")
    public String guardarTurno(@Validated Turno turno, BindingResult bindingResult, RedirectAttributes redirect, Model modelo) {
    	if(bindingResult.hasErrors()) {
    		modelo.addAttribute("turno", turno);
    		return "nuevoTurno";
    	}
    	
    	turnoRepo.save(turno);
    	redirect.addFlashAttribute("msgExito", "El turno ha sido guardado exitosamente");
    	return "redirect:/turnos";
    }
    
    @GetMapping ("/{id}/editarTurno")
	public String editarTurno(@PathVariable Long id, Model modelo) {
    	Turno turno = turnoRepo.getById(id);
    	modelo.addAttribute("turno", turno);
    	return "nuevoTurno";
    }

    @PostMapping("/{id}/editarTurno")
    public String actualizarTurno(@PathVariable Long id, @Validated Turno turno, BindingResult bindingResult, RedirectAttributes redirect, Model modelo) {
    	Turno turnoDB = turnoRepo.getById(id);
    	if(bindingResult.hasErrors()) {
    		modelo.addAttribute("turno", turno);
    		return "nuevoTurno";
    	}
    	
    	turnoDB.setTipo(turno.getTipo());
    	turnoRepo.save(turnoDB);
    	redirect.addFlashAttribute("msgExito", "El turno ha sido actualizado con exito.");
    	return "redirect:/turnos";
    }
    
    @GetMapping("/find/{id}")
    public ResponseEntity<Turno> getTurnoById(@PathVariable("id") Long id){
    	Turno turno = turnoServicio.findTurnoById(id);
        return new ResponseEntity<>(turno, HttpStatus.OK);
    }
    //Consulta 1 Sofia
    @GetMapping("consultas/{id}")
    public ResponseEntity<?> cochesParaUnTurnow(@RequestParam Long id){
        return ResponseEntity.status(HttpStatus.OK).body(turnoServicio.cochesParaUnTurno(id));
    }

    //Consulta 1
    public List<Coche> cochesParaUnTurno(Long id){
		ResponseEntity<Turno> turno = getTurnoById(id);
		return turno.getBody().getListaCoches();
	}
    
}
