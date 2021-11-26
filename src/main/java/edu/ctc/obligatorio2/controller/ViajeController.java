package edu.ctc.obligatorio2.controller;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.ctc.obligatorio2.entity.Chofer;
import edu.ctc.obligatorio2.entity.Coche;
import edu.ctc.obligatorio2.entity.Turno;
import edu.ctc.obligatorio2.entity.Viaje;
import edu.ctc.obligatorio2.service.ViajeServicio;

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


	@RequestMapping("/consultas")
	//Consulta 2
	public List<Viaje> viajesPorChoferPorFecha(Chofer chofer, LocalDateTime fecha){
		List<Viaje> todosLosViajes = (List<Viaje>) getAllViajes(); //cast
		List<Viaje> retorno = todosLosViajes;
		retorno.clear(); //No pregunten
		
		for(Viaje viaje : todosLosViajes) {
			if(viaje.getChofer().equals(chofer) && viaje.getFechaHora().toLocalDate().equals(fecha.toLocalDate())) {
				retorno.add(viaje);
				continue;
			}
		}
		return retorno;
	}
	
	public List<Viaje> viajesPorChoferPorFecha(Chofer chofer, LocalDateTime fecha1, LocalDateTime fecha2){
		List<Viaje> todosLosViajes = (List<Viaje>) getAllViajes(); //cast
		List<Viaje> retorno = todosLosViajes;
		retorno.clear(); //No pregunten
		
		for(Viaje viaje : todosLosViajes) {
			if(viaje.getChofer().equals(chofer) && (viaje.getFechaHora().toLocalDate().isAfter(fecha1.toLocalDate()) || viaje.getFechaHora().toLocalDate().equals(fecha1.toLocalDate())) && (viaje.getFechaHora().toLocalDate().isBefore(fecha2.toLocalDate()) || viaje.getFechaHora().toLocalDate().equals(fecha2.toLocalDate()))) {
				retorno.add(viaje);
				continue;
			}
		}
		return retorno;
	}
	
	
	//Consulta 3
	public float recaudadoPorChoferPorViaje(Chofer chofer, Viaje pViaje) {
		List<Viaje> todosViajes = (List<Viaje>) getAllViajes(); //cast
		float contador = 0;
		
		for(Viaje viaje : todosViajes) {
    		if(viaje.equals(pViaje)) {
    			contador += viaje.getPrecio();
    		}
    	}
		return contador;
	}
	
	public float recaudadoPorChoferPorViaje(Chofer chofer, Turno turno) {
		List<Viaje> todosViajes = (List<Viaje>) getAllViajes(); //cast
		float contador = 0;
		
		for(Viaje viaje : todosViajes) {
    		if(viaje.getTurno().equals(turno)) {
    			contador += viaje.getPrecio();
    		}
    	}
		return contador;
	}
	
	public float recaudadoPorChoferPorViaje(Chofer chofer, LocalDateTime fecha) {
		List<Viaje> todosViajes = (List<Viaje>) getAllViajes(); //cast
		float contador = 0;
		
		for(Viaje viaje : todosViajes) {
    		if(viaje.getFechaHora().toLocalDate().equals(fecha.toLocalDate())) {
    			contador += viaje.getPrecio();
    		}
    	}
		return contador;
	}
	
	
	//Consulta 4
    public float kmRecorridosPorAutoPorDia(Coche coche, LocalDateTime fecha) {
    	List<Viaje> todosViajes = (List<Viaje>) getAllViajes(); //cast
    	float contador = 0;
    	
    	for(Viaje viaje : todosViajes) {
    		if(viaje.getFechaHora().toLocalDate().equals(fecha.toLocalDate())) {
    			contador += viaje.getKmRecorridos();
    		}
    	}
    	return contador;
    }
    
    public float kmRecorridosPorAutoPorMes(Coche coche, LocalDateTime fecha) {
    	List<Viaje> todosViajes = (List<Viaje>) getAllViajes(); //cast
    	float contador = 0;
    	
    	for(Viaje viaje : todosViajes) {
    		if(viaje.getFechaHora().getMonth().equals(fecha.getMonth()) && viaje.getFechaHora().getYear() == fecha.getYear()) {
    			contador += viaje.getKmRecorridos();
    		}
    	}
    	return contador;
    }
    
    public float kmRecorridosPorAutoPorChofer(Coche coche, Chofer chofer) {
    	List<Viaje> todosViajes = (List<Viaje>) getAllViajes(); //cast
    	float contador = 0;
    	
    	for(Viaje viaje : todosViajes) {
    		if(viaje.getChofer().equals(chofer)) {
    			contador += viaje.getKmRecorridos();
    		}
    	}
    	return contador;
    }
}
