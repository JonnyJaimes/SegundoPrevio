package com.example.demo.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entities.Categoria;
import com.example.demo.repository.CategoriaRepository;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/categorias")
public class CategoriaViewController {

	@Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping
    public String listarCategorias(Model model) {
        List<Categoria> categorias = categoriaRepository.findAll();
        model.addAttribute("categorias", categorias);
        return "index";
    }
    
	
	@GetMapping("/form")
    public String mostrar(@RequestParam(value = "id", required = false) Integer id, Model model) {
        Categoria categoria = new Categoria();
        if(id != null) {
			categoria = categoriaRepository.findById(id).get();
		}
		
		model.addAttribute("categoria", categoria);
		return "agregar";
    }

	
	@PostMapping("/agregar")
	public String agregar(@Valid Categoria categoria, BindingResult result) {
		if (result.hasErrors()) {
            return "agregar";
        }
		categoriaRepository.save(categoria);
		
		return "redirect:/categorias";
	}
	
	@GetMapping("/editar")
	public String actualizarCategoria(@RequestParam("id") int id, @ModelAttribute("categoria") Categoria categoriaActualizada,
			BindingResult result) {
		Categoria categoria = categoriaRepository.findById(id)
             .orElseThrow(() -> new IllegalArgumentException("Categoria no encontrada con id: " + id));
		categoria.setDescripcion(categoriaActualizada.getDescripcion());
        categoriaRepository.save(categoria);
	    return "redirect:/categorias"; 
	}
	
	@GetMapping("/borrar/{id}")
    public String eliminarCategoria(@PathVariable("id") Integer id) {

        Categoria categoria = categoriaRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Categoria no encontrada con id: " + id));
        categoriaRepository.delete(categoria);
        return "redirect:/categorias";
    }
	
}