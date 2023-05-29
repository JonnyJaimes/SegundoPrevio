package com.example.demo.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="resultado")
@Data
public class Resultado {

    @Id
    @SequenceGenerator(name="resultado_id_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="resultado_id_seq")
    private Integer id;

    @OneToOne
    @JoinColumn(name = "partido_id")
    private Partido partido;

    @OneToOne
    @JoinColumn(name = "seleccion_id")
    private Seleccion seleccion;

    @Column(name = "goles")
    private Integer goles;

    @Column(name = "amarillas")
    private Integer amarillas;

    @Column(name = "rojas")
    private Integer rojas;
}