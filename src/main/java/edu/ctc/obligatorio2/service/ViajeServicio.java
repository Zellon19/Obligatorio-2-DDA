package edu.ctc.obligatorio2.service;

import edu.ctc.obligatorio2.entity.Viaje;
import edu.ctc.obligatorio2.exception.ViajeNotFoundException;
import edu.ctc.obligatorio2.repository.ViajeRepo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ViajeServicio {
	private final ViajeRepo viajeRepo;
	
	@Autowired
	public ViajeServicio(ViajeRepo pViajeRep) {
		this.viajeRepo = pViajeRep;
	}
	
	public Viaje addViaje(Viaje pViaje) {
		return viajeRepo.save(pViaje);
	}
	
	public List<Viaje> findAllViajes(){
		return viajeRepo.findAll();
	}
	
	public Viaje findViajeById(Long pId) {
		return viajeRepo.findViajeById(pId)
				.orElseThrow(()-> new ViajeNotFoundException("El viaje con la id " + pId + " no fue encontrado."));
	}
	
	public Viaje updateViaje(Viaje pViaje) {
		return viajeRepo.save(pViaje);
	}
	
	public void deleteViaje(Long pId) {
		viajeRepo.deleteViajeById(pId);
	}
	
}
