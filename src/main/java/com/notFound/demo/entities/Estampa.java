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
@Table(name = "estampa")
public class Estampa {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "precio", nullable = false)
    private Integer precio;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "descripcion", nullable = false, length = 50)
    private String descripcion;

    @Column(name = "visibilidad", nullable = false)
    private Boolean visibilidad = false;

    @Column(name = "stock", nullable = false, length = 50)
    private String stock;

    @Column(name = "calificacion", nullable = false, precision = 2, scale = 1)
    private BigDecimal calificacion;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_tema", nullable = false)
    private Tema idTema;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_artista", nullable = false)
    private Artista idArtista;

    @OneToMany(mappedBy = "idEstampa")
    private Set<CamisetaEstampa> camisetaEstampas = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idEstampa")
    private Set<Imagene> imagenes = new LinkedHashSet<>();

}