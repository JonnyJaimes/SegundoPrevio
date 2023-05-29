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

import com.example.demo.repository.CategoriaRepository;
import com.example.demo.repository.NewsRepository;
import com.example.demo.entities.Categoria;
import com.example.demo.entities.News;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/news")
public class NewsController {

	@Autowired
	private NewsRepository newsRepository;
	
	@Autowired
    private CategoriaRepository categoriaRepository;
	
	@GetMapping
    public String listarNews(Model model) {
        List<News> news = newsRepository.findAll();
        
        model.addAttribute("news", news);
        return "noticia";
    }
	
	@GetMapping("/form")
    public String mostrar(@RequestParam(value = "id", required = false) Integer id, Model model) {
		News news = new News();
		
		if(id != null) {
			news = newsRepository.findById(id).get();
		}
		List<Categoria> categorias = categoriaRepository.findAll();
		model.addAttribute("categorias", categorias);
		model.addAttribute("news", news);
        return "nueva";
    }
	
	@PostMapping("/agregar")
	public String agregar(@Valid News noticia, BindingResult result) {
		if (result.hasErrors()) {
            return "nueva";
        }
		newsRepository.save(noticia);
		
		return "redirect:/news";
	}
	
	@GetMapping("/editar")
	public String actualizarNoticia(@RequestParam("id") int id, @ModelAttribute("news") News noticiaActualizada, BindingResult result) {
		News noticia = newsRepository.findById(id)
             .orElseThrow(() -> new IllegalArgumentException("Noticia no encontrada con id: " + id));
		noticia.setTitulo(noticiaActualizada.getTitulo());
		noticia.setDesarrollo(noticiaActualizada.getDesarrollo());
		noticia.setFecha(noticiaActualizada.getFecha());
		noticia.setCategoria(noticiaActualizada.getCategoria());
		noticia.setUrl(noticiaActualizada.getUrl());
		noticia.setResumen(noticiaActualizada.getResumen());
        newsRepository.save(noticia);
	    return "redirect:/news"; 
	}
	
	@GetMapping("/borrar/{id}")
    public String eliminarCategoria(@PathVariable("id") Integer id) {

        News noticia = newsRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Noticia no encontrada con id: " + id));
        newsRepository.delete(noticia);
        return "redirect:/news";
    }
	

}