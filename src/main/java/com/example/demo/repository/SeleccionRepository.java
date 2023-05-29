package com.example.demo.repository;



import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Seleccion;

public interface SeleccionRepository extends JpaRepository<Seleccion,Integer> {
	 List<Seleccion> findByGrupo(String grupo);
	 Optional<Seleccion> findById(Long id);
	void deleteById(Long id);
}