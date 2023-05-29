package com.example.demo.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Resultado;

public interface ResultadoRepository extends JpaRepository<Resultado,Integer> {
}