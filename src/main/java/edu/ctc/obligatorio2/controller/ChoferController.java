package edu.ctc.obligatorio2.controller;

import edu.ctc.obligatorio2.entity.Chofer;
import edu.ctc.obligatorio2.repository.ChoferRepo;
import edu.ctc.obligatorio2.service.ChoferServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller

public class ChoferController {
    private final ChoferServicio choferServicio;
    @Autowired
    private ChoferRepo choferRepo;

    public ChoferController(ChoferServicio choferServicio) {
        this.choferServicio = choferServicio;
    }

    @GetMapping({ "/", "" })
    public String verPaginaDeInicio(Model modelo) {
        List<Chofer> choferes = choferRepo.findAll();
        modelo.addAttribute("choferes", choferes);
        return "index.html";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioDeRegistrarChofer(Model modelo) {
        modelo.addAttribute("chofer", new Chofer());
        return "nuevo";
    }

    @PostMapping("/nuevo")
    public String guardarChofer(@Validated Chofer chofer, BindingResult bindingResult, RedirectAttributes redirect, Model modelo) {
        if (bindingResult.hasErrors()) {
            modelo.addAttribute("chofer", chofer);
            return "nuevo";
        }

        choferRepo.save(chofer);
        redirect.addFlashAttribute("msgExito", "El chofer ha sido agregado con exito");
        return "redirect:/";
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
