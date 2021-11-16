package edu.ctc.obligatorio2.controller;

import edu.ctc.obligatorio2.entity.Coche;
import edu.ctc.obligatorio2.service.CocheServicio;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/coche")
public class CocheController {
    private final CocheServicio cocheServicio;

    public CocheController(CocheServicio cocheServicio) {
        this.cocheServicio = cocheServicio;
    }

    //devuelve el http response con todos los choferes
    @GetMapping("/todos")
    public ResponseEntity<List<Coche>> getAllCoches(){
        List<Coche> coches = cocheServicio.findAllCoches();
        return new ResponseEntity<>(coches, HttpStatus.OK);
    }

    //le pasamos el id a la request
    @GetMapping("/find/{id}")
    public ResponseEntity<Coche> getCocheById(@PathVariable("id") Long id){
    	Coche coche = cocheServicio.findCocheById(id);
        return new ResponseEntity<>(coche, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Coche> addCoche(@RequestBody Coche pCoche){
    	Coche coche = cocheServicio.addCoche(pCoche);
        return new ResponseEntity<>(coche, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Coche> updateCoche(@RequestBody Coche pCoche){
    	Coche coche = cocheServicio.updateCoche(pCoche);
        return new ResponseEntity<>(coche, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCoche(@PathVariable("id") Long id){
        cocheServicio.deleteCoche(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
