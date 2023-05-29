package com.example.demo.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Partido;

public interface PartidoRepository extends JpaRepository<Partido,Integer> {
}
