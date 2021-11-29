package edu.ctc.obligatorio2.controller;

import java.time.LocalDateTime;
import java.util.List;

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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.ctc.obligatorio2.entity.Chofer;
import edu.ctc.obligatorio2.entity.Coche;
import edu.ctc.obligatorio2.entity.TipoChofer;
import edu.ctc.obligatorio2.entity.Turno;
import edu.ctc.obligatorio2.entity.Viaje;
import edu.ctc.obligatorio2.repository.ViajeRepo;
import edu.ctc.obligatorio2.service.ChoferServicio;
import edu.ctc.obligatorio2.service.CocheServicio;
import edu.ctc.obligatorio2.service.ViajeServicio;

@Controller
@RequestMapping("/viajes")
public class ViajeController {

	@Autowired
	ViajeRepo viajeRepo;

	private final ViajeServicio viajeServicio;
	private final ChoferServicio choferServicio;
	private final CocheServicio cocheServicio;
	public ViajeController(ViajeServicio pViajeServ, ChoferServicio pChoferServ, CocheServicio pCocheServ) {
		this.viajeServicio = pViajeServ;
		this.choferServicio = pChoferServ;
		this.cocheServicio = pCocheServ;
	}


	@GetMapping({ "/", "" })
	public String pagListaViajes(Model modelo) {
		List<Viaje> viajes = viajeServicio.findAllViajes();
		modelo.addAttribute("viajes", viajes);
		return "viajes.html";
	}

	@GetMapping("/nuevoViaje")
	public String mostrarFormularioDeRegistrarViaje(Model modelo) {
		modelo.addAttribute("viaje", new Viaje());
		return "nuevoViaje";
	}

	@PostMapping("/nuevoViaje")
	public String guardarViaje(@Validated Viaje viaje, BindingResult bindingResult, RedirectAttributes redirect, Model modelo) {
		if (bindingResult.hasErrors()) {
			modelo.addAttribute("viaje", viaje);
			return "nuevoViaje";
		}
		Chofer chofer = choferServicio.findChoferById(viaje.getChofer().getId());
		
		 //Check de que suplentes no hagan mas de un turno por dia, que un chofer no use autos distintos en el turno y que choferes distintos no usen el mismo auto en el mismo turno
			List<Viaje> viajes = getAllViajes().getBody();
			Turno turno = null;
			Coche coche = null;
			for(Viaje iViaje: viajes) {
				if(iViaje.getChofer().equals(chofer) && iViaje.getFechaHora().toLocalDate().equals(viaje.getFechaHora().toLocalDate())){
					turno = iViaje.getTurno();
					coche = iViaje.getCoche();
					break;
				}
			}
			
			
			List<Coche> cochesUsadosPorOtros = cocheServicio.findAllCoches();
			cochesUsadosPorOtros.clear();
			
			for(Viaje iViaje: viajes) {
				if(!iViaje.getChofer().equals(chofer) && iViaje.getFechaHora().toLocalDate().equals(viaje.getFechaHora().toLocalDate()) && iViaje.getTurno().getId() == viaje.getTurno().getId()){
					cochesUsadosPorOtros.add(iViaje.getCoche());
				}
			}
			
			Boolean bool = true;
			for(Coche iCoche: cochesUsadosPorOtros) {
				if(viaje.getCoche().getId() == iCoche.getId()) {
					bool = false;
					break;
				}
			}
			
			if(chofer.getTipoChofer().equals(TipoChofer.Suplente) && (turno == null || viaje.getTurno().getId() == turno.getId()) && (coche == null || viaje.getCoche().getId() == coche.getId()) && bool) {
				viajeRepo.save(viaje);
				redirect.addFlashAttribute("msgExito", "El viaje ha sido agregado con exito");
				return "redirect:/viajes";
			}
			else if(chofer.getTipoChofer().equals(TipoChofer.Suplente)) {
				modelo.addAttribute("viaje", viaje);
				return "nuevoViaje";
			}
		
			if((coche == null || viaje.getCoche().getId() == coche.getId()) && bool) {
				viajeRepo.save(viaje);
				redirect.addFlashAttribute("msgExito", "El viaje ha sido agregado con exito");
				return "redirect:/viajes";
			}
			else{
				modelo.addAttribute("viaje", viaje);
				return "nuevoViaje";
			}
	}

	@GetMapping("/{id}/editarViaje")
	public String editarViaje(@PathVariable Long id, Model modelo) {
		Viaje viaje = viajeRepo.getById(id);
		modelo.addAttribute("viaje", viaje);
		return "nuevoViaje";
	}

	@PostMapping("/{id}/editarViaje")
	public String actualizarViaje(@PathVariable Long id, @Validated Viaje viaje,
								  BindingResult bindingResult, RedirectAttributes redirect, Model modelo) {
		Viaje viajeDB = viajeRepo.getById(id);
		if (bindingResult.hasErrors()) {
			modelo.addAttribute("viaje", viaje);
			return "nuevoViaje";
		}

		viajeDB.setDireccion(viaje.getDireccion());
		viajeDB.setKmRecorridos(viaje.getKmRecorridos());
		viajeDB.setfechaHora(viaje.getFechaHora());
		viajeDB.setPrecio(viaje.getPrecio());
		viajeDB.setChofer(viaje.getChofer());
		viajeDB.setTurno(viaje.getTurno());
		viajeDB.setCoche(viaje.getCoche());

		viajeRepo.save(viajeDB);
		redirect.addFlashAttribute("msgExito", "El viaje ha sido actualizado correctamente");
		return "redirect:/viajes";
	}

	@PostMapping("/{id}/eliminar")
	public String eliminarViaje(@PathVariable Long id, RedirectAttributes redirect) {
		Viaje viaje = viajeRepo.getById(id);
		viajeRepo.delete(viaje);
		redirect.addFlashAttribute("msgExito", "El viaje ha sido eliminado correctamente");
		return "redirect:/viajes";
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

}
