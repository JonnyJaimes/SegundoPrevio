package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
@Entity
@Data
@Table(name = "resultado")
public class Resultado implements Serializable {
    @Id
    private int id;
    @ManyToOne
    @JoinColumn(name = "id",insertable = false,updatable = false)
    private Partido partido_id;
    @ManyToOne
    @JoinColumn(name = "id",insertable = false,updatable = false)
    private Seleccion seleccion_id;
    private Integer goles;
    private Integer amarillas;
    private Integer rojas;
}