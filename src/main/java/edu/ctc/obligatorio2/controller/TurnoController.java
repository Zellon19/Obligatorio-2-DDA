package edu.ctc.obligatorio2.controller;

import edu.ctc.obligatorio2.entity.Coche;
import edu.ctc.obligatorio2.entity.Turno;
import edu.ctc.obligatorio2.service.TurnoServicio;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
 @RequestMapping("/turno")
public class TurnoController {
    private final TurnoServicio turnoServicio;

    public TurnoController(TurnoServicio turnoServicio) {
        this.turnoServicio = turnoServicio;
    }

    //devuelve el http response con todos los choferes
    @GetMapping("/todos")
    public ResponseEntity<List<Turno>> getAllTurnos(){
        List<Turno> turnos = turnoServicio.findAllTurnos();
        return new ResponseEntity<>(turnos, HttpStatus.OK);
    }

    //le pasamos el id a la request
    @GetMapping("/find/{id}")
    public ResponseEntity<Turno> getTurnoById(@PathVariable("id") Long id){
    	Turno turno = turnoServicio.findTurnoById(id);
        return new ResponseEntity<>(turno, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Turno> addTurno(@RequestBody Turno pTurno){
    	Turno turno = turnoServicio.addTurno(pTurno);
        return new ResponseEntity<>(turno, HttpStatus.CREATED);
    }
    
    @PutMapping ("/{id}")
	public ResponseEntity <Turno> updateTurno (@RequestBody Turno pTurno, 
			@PathVariable(value = "id") Long id) throws Exception{
    		Turno turno = turnoServicio.findTurnoById(id);
		
		turno.setTipo(pTurno.getTipo());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(turnoServicio.updateTurno(turno));
	}

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteTurno(@PathVariable("id") Long id){
        turnoServicio.deleteTurno(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
