package edu.ctc.obligatorio2.controller;

import java.time.LocalDateTime;
import java.util.List;

import edu.ctc.obligatorio2.repository.CocheRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.ctc.obligatorio2.entity.Coche;
import edu.ctc.obligatorio2.entity.Viaje;
import edu.ctc.obligatorio2.service.CocheServicio;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/coches")
public class CocheController {
    private final CocheServicio cocheServicio;
    CocheRepo cocheRepo;

    @Autowired
    public CocheController(CocheServicio cocheServicio) {
        this.cocheServicio = cocheServicio;
    }

    //devuelve el http response con todos los choferes

    @GetMapping({ "/", "" })
    public String pagListaCoches(Model modelo) {
        List<Coche> coches = cocheServicio.findAllCoches();
        modelo.addAttribute("coches", coches);
        return "coches.html";
    }

    @GetMapping("/nuevoCoche")
    public String mostrarFormularioDeRegistrarCoche(Model modelo) {
        modelo.addAttribute("coche", new Coche());
        return "nuevoCoche";
    }

    @PostMapping("/nuevoCoche")
    public String guardarCoche(@Validated Coche coche, BindingResult bindingResult, RedirectAttributes redirect, Model modelo) {
        if (bindingResult.hasErrors()) {
            modelo.addAttribute("coche", coche);
            return "nuevoCoche";
        }

        cocheRepo.save(coche);
        redirect.addFlashAttribute("msgExito", "El coche ha sido agregado con exito");
        return "redirect:/coches";
    }

    @GetMapping("/{id}/editarCoche")
    public String editarCoche(@PathVariable Long id, Model modelo) {
        Coche coche = cocheRepo.getById(id);
        modelo.addAttribute("coche", coche);
        return "nuevoCoche";
    }

    @PostMapping("/{id}/editarCoche")
    public String actualizarCoche(@PathVariable Long id, @Validated Coche coche,
                                     BindingResult bindingResult, RedirectAttributes redirect, Model modelo) {
        Coche cocheDB = cocheRepo.getById(id);
        if (bindingResult.hasErrors()) {
            modelo.addAttribute("coche", coche);
            return "nuevoCoche";
        }

        cocheDB.setMatricula(coche.getMatricula());
        cocheRepo.save(cocheDB);
        redirect.addFlashAttribute("msgExito", "El coche ha sido actualizado correctamente");
        return "redirect:/coches";
    }

    @PostMapping("coches/{id}/eliminar")
    public String eliminarCoche(@PathVariable Long id, RedirectAttributes redirect) {
        Coche coche=  cocheRepo.getById(id);
        cocheRepo.delete(coche);
        redirect.addFlashAttribute("msgExito", "El chofer ha sido eliminado correctamente");
        return "redirect:/coches";
    }


}
