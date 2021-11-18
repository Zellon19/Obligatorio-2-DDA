package edu.ctc.obligatorio2.controller;

import edu.ctc.obligatorio2.entity.Chofer;
import edu.ctc.obligatorio2.service.ChoferServicio;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/chofer")
public class ChoferController {
    private final ChoferServicio choferServicio;

    public ChoferController(ChoferServicio choferServicio) {
        this.choferServicio = choferServicio;
    }

    //devuelve el http response con todos los choferes
    @GetMapping("/todos")
    public ResponseEntity<List<Chofer>> getAllChoferes(){
        List<Chofer> choferes = choferServicio.findAllChoferes();
        return new ResponseEntity<>(choferes, HttpStatus.OK);
    }

    //le pasamos el id a la request
    @GetMapping("/find/{id}")
    public ResponseEntity<Chofer> getChoferById(@PathVariable("id") Long id){
        Chofer employee = choferServicio.findChoferById(id);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Chofer> addChofer(@RequestBody Chofer chofer){
        Chofer newChofer = choferServicio.addChofer(chofer);
        return new ResponseEntity<>(newChofer, HttpStatus.CREATED);
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<Chofer> updateChofer(@RequestBody Chofer chofer){
//        Chofer updateChofer = choferServicio.updateChofer(chofer);
//        return new ResponseEntity<>(updateChofer, HttpStatus.OK);
//    }
    
    @PutMapping ("/{id}")
	public ResponseEntity <Chofer> updateChofer (@RequestBody Chofer pChofer, 
			@PathVariable(value = "id") Long id) throws Exception{
			Chofer chofer = choferServicio.findChoferById(id);
		
		chofer.setNombre(pChofer.getNombre());
		chofer.setApellido(pChofer.getApellido());
		//chofer.setTelefono(pChofer.getTelefono());
		//chofer.setCedula(pChofer.getCedula());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(choferServicio.updateChofer(chofer));
	}

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteChofer(@PathVariable("id") Long id){
        choferServicio.deleteChofer(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
