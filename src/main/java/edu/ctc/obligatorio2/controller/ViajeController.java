package edu.ctc.obligatorio2.controller;

import edu.ctc.obligatorio2.entity.Coche;
import edu.ctc.obligatorio2.entity.Viaje;
import edu.ctc.obligatorio2.service.ViajeServicio;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/viaje")
public class ViajeController {
	private final ViajeServicio viajeServicio;
	
	public ViajeController(ViajeServicio pViajeServ) {
		this.viajeServicio = pViajeServ;
	}
	
	//devuelve todos los viajes
	@GetMapping("/todos")
	public ResponseEntity<List<Viaje>> getAllViajes(){
		List<Viaje> viajes = viajeServicio.findAllViajes();
		return new ResponseEntity<>(viajes, HttpStatus.OK);
	}
	
	//devuelve un viaje
	@GetMapping("/find/{id}")
	public ResponseEntity<Viaje> getViajeById(@PathVariable("id") Long pId){
		Viaje viaje = viajeServicio.findViajeById(pId);
		return new ResponseEntity<>(viaje, HttpStatus.OK);
	}
	
	//agrega un viaje
	@PostMapping("/add")
	public ResponseEntity<Viaje> addViaje(@RequestBody Viaje pViaje){
		Viaje viaje = viajeServicio.addViaje(pViaje);
		return new ResponseEntity<>(viaje, HttpStatus.CREATED);
	}
	
	//modifica un viaje
	public ResponseEntity<Viaje> updateViaje(@RequestBody Viaje pViaje){
		Viaje viaje = viajeServicio.updateViaje(pViaje);
		return new ResponseEntity<>(viaje, HttpStatus.OK);
	}
	
	@PutMapping ("/{id}")
	public ResponseEntity <Viaje> updateViaje (@RequestBody Viaje pViaje, 
			@PathVariable(value = "id") Long id) throws Exception{
    		Viaje viaje = viajeServicio.findViajeById(id);
		
		viaje.setDireccion(pViaje.getDireccion());
		viaje.setfechaHora(pViaje.getFechaHora());
		viaje.setKmRecorridos(pViaje.getKmRecorridos());
		viaje.setPrecio(pViaje.getPrecio());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(viajeServicio.updateViaje(viaje));
	}
	
	//elimina un viaje
	public ResponseEntity<?> deleteViaje(@PathVariable("id") Long pId){
		viajeServicio.deleteViaje(pId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
