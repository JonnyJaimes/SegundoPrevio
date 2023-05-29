package com.example.demo.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class News {
	
	@Id
	@SequenceGenerator(name="news_id_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="news_id_seq")
	private Integer id;
	
	@Column(length=500)
	private String titulo;
	
	private String desarrollo;
	
	private LocalDate fecha;
	
	@ManyToOne
    @JoinColumn(name = "categoria_id")
	private Categoria categoria;
	
	@Column(length=500)
	private String url;
	
	private String resumen;
}