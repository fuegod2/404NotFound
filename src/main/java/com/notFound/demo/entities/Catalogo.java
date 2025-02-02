package com.notFound.demo.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Immutable;

import java.math.BigDecimal;

/**
 * Mapping for DB view
 */
@Getter
@Setter
@Entity
@Immutable
@Table(name = "catalogo")
public class Catalogo {
    @Id
    @Column(name = "id_estampa", nullable = false)
    private Integer id;

    @Column(name = "nombre_estampa", length = 50)
    private String nombreEstampa;

    @Column(name = "descripcion", length = 2000)
    private String descripcion;

    @Column(name = "stock")
    private Integer stock;

    @Column(name = "calificacion", precision = 10, scale = 2)
    private BigDecimal calificacion;

    @Column(name = "id_tema")
    private Integer idTema;

    @Column(name = "nombre_tema", length = 50)
    private String nombreTema;

    @Column(name = "nombre_artista", length = 50)
    private String nombreArtista;

    @Column(name = "apellido", length = 50)
    private String apellido;

    @Column(name = "id_artista")
    private Integer idArtista;

    @Column(name = "enlace", length = 2000)
    private String imagen;

    @Column(name = "precio", precision = 10, scale = 2)
    private BigDecimal precio;

}