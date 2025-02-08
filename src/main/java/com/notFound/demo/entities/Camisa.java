package com.notFound.demo.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "camisa")
public class Camisa {
    @Id
    @Column(name = "id_camisa", nullable = false)
    private Integer id;

    @Column(name = "talla", nullable = false, length = 3)
    private String talla;

    @Column(name = "color", nullable = false, length = 25)
    private String color;

    @Column(name = "material", nullable = false, length = 50)
    private String material;

    @Column(name = "tipo", nullable = false, length = 1)
    private String tipo;

    @Column(name = "precio_camisa", nullable = false, precision = 10, scale = 2)
    private BigDecimal precioCamisa;

    @Column(name = "stock", nullable = false)
    private Integer stock;

    @OneToMany(mappedBy = "idCamisa")
    private Set<CamisaEstampa> camisaEstampas = new LinkedHashSet<>();

}