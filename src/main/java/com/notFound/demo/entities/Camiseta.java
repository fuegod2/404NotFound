package com.notFound.demo.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "camiseta")
public class Camiseta {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "talla", nullable = false, length = Integer.MAX_VALUE)
    private String talla;

    @Column(name = "color", nullable = false, length = 20)
    private String color;

    @Column(name = "material", nullable = false, length = 50)
    private String material;

    @Column(name = "tipo", nullable = false, length = 2)
    private String tipo;

    @Column(name = "precio", nullable = false)
    private Integer precio;

    @Column(name = "stock")
    private Integer stock;

    @OneToMany(mappedBy = "idCamiseta")
    private Set<CamisetaEstampa> camisetaEstampas = new LinkedHashSet<>();

}