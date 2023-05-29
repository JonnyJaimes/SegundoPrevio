package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Data
@Table(name = "seleccion")
public class Seleccion implements Serializable {
    @Id
    private int id;
    private String nombre;
    @ManyToOne
    @JoinColumn(name = "id",insertable = false,updatable = false)
    private Continente continente_id;
    private String grupo;

}