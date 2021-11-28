package edu.ctc.obligatorio2.controller;

import edu.ctc.obligatorio2.entity.Chofer;
import edu.ctc.obligatorio2.repository.ChoferRepo;
import edu.ctc.obligatorio2.service.ChoferServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller
public class ChoferController {
	
    private final ChoferServicio choferServicio;
    @Autowired
    ChoferRepo choferRepo;

    public ChoferController(ChoferServicio choferServicio) {
        this.choferServicio = choferServicio;
    }

    @GetMapping("/home")
    public String mostrarHome() {
        return "home";
    }


    @GetMapping({ "/choferes", "" })
    public String pagListaChoferes(Model modelo) {
        List<Chofer> choferes = choferServicio.findAllChoferes();
        modelo.addAttribute("choferes", choferes);
        return "choferes.html";
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

    @GetMapping("/{id}/editar")
    public String editarChofer(@PathVariable Long id, Model modelo) {
        Chofer chofer = choferRepo.getById(id);
        modelo.addAttribute("chofer", chofer);
        return "nuevo";
    }

    @PostMapping("/{id}/editar")
    public String actualizarContacto(@PathVariable Long id, @Validated Chofer chofer,
                                     BindingResult bindingResult, RedirectAttributes redirect, Model modelo) {
        Chofer choferDB = choferRepo.getById(id);
        if (bindingResult.hasErrors()) {
            modelo.addAttribute("chofer", chofer);
            return "nuevo";
        }

        choferDB.setNombre(chofer.getNombre());
        choferDB.setApellido(chofer.getApellido());
        choferDB.setTelefono(chofer.getTelefono());
        choferDB.setCedula(chofer.getCedula());
        choferDB.setTipoChofer(chofer.getTipoChofer());

        choferRepo.save(choferDB);
        redirect.addFlashAttribute("msgExito", "El chofer ha sido actualizado correctamente");
        return "redirect:/";
    }

    @PostMapping("/{id}/eliminar")
    public String eliminarChofer(@PathVariable Long id, RedirectAttributes redirect) {
        Chofer chofer= choferRepo.getById(id);
        choferRepo.delete(chofer);
        redirect.addFlashAttribute("msgExito", "El chofer ha sido eliminado correctamente");
        return "redirect:/";
    }


}
