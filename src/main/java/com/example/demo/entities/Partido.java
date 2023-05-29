package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@Table(name = "partido")
public class Partido implements Serializable {
    @Id
    private int id;
    private Date fecha;
    @ManyToOne
    @JoinColumn(name = "id",insertable = false,updatable = false)
    private Estadio estadio_id;
}
