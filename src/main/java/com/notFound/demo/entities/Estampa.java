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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estampa", nullable = false)
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "descripcion", nullable = false, length = 2000)
    private String descripcion;

    @Column(name = "visibilidad", nullable = false)
    private Boolean visibilidad = false;

    @Column(name = "stock", nullable = false)
    private Integer stock;

    @Column(name = "calificacion", nullable = false, precision = 10, scale = 2)
    private BigDecimal calificacion;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_tema", nullable = false)
    private Tema idTema;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_artista", nullable = false)
    private Artista idArtista;

    @Column(name = "precio", precision = 10, scale = 2)
    private BigDecimal precio;

    @OneToMany(mappedBy = "idEstampa")
    private Set<CamisaEstampa> camisaEstampas = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idEstampa")
    private Set<Imagen> imagens = new LinkedHashSet<>();

}