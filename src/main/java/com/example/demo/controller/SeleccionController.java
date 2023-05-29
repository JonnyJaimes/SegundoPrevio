package com.example.demo.controller;

import com.example.demo.entities.Seleccion;
import com.example.demo.repository.SeleccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

@Controller
@RequestMapping("/selecciones")
public class SeleccionController {

    @Autowired
    SeleccionRepository seleccionRepo;

    @GetMapping
    public String getSeleccionAll(Model model) {
        List<Seleccion> selecciones = seleccionRepo.findAll();
        model.addAttribute("selecciones", selecciones);
        return "selecciones";
    }

    @GetMapping("/{grupo}")
    public String getSeleccionByGrupo(@PathVariable String grupo, Model model) {
        List<Seleccion> selecciones = seleccionRepo.findByGrupo(grupo);
        model.addAttribute("selecciones", selecciones);
        return "selecciones";
    }

    @PostMapping
    public String postSeleccion(@ModelAttribute Seleccion seleccion) {
        seleccionRepo.save(seleccion);
        return "redirect:/selecciones";
    }

    @GetMapping("/edit/{id}")
    public String editSeleccion(@PathVariable Long id, Model model) {
        Seleccion seleccion = seleccionRepo.findById(id).orElse(null);
        model.addAttribute("seleccion", seleccion);
        return "edit_seleccion";
    }

    @PostMapping("/update/{id}")
    public String updateSeleccion(@PathVariable Long id, @ModelAttribute Seleccion updatedSeleccion) {
        Seleccion seleccion = seleccionRepo.findById(id).orElse(null);
        if (seleccion != null) {
            seleccion.setNombre(updatedSeleccion.getNombre());
            seleccion.setGrupo(updatedSeleccion.getGrupo());
            seleccionRepo.save(seleccion);
        }
        return "redirect:/selecciones";
    }
    @GetMapping("/delete/{id}")
    public String deleteSeleccion(@PathVariable Long id) {
        seleccionRepo.deleteById(id);
        return "redirect:/selecciones";
    }

    
   
}

