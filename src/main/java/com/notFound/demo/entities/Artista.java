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
@Table(name = "artista")
public class Artista {
    @Id
    @Column(name = "id_artista", nullable = false)
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "apellido", length = 50)
    private String apellido;

    @Column(name = "usuario", nullable = false, length = 50)
    private String usuario;

    @Column(name = "contrasena", nullable = false, length = 50)
    private String contrasena;

    @Column(name = "tipo_id", nullable = false, length = 2)
    private String tipoId;

    @Column(name = "correo", nullable = false, length = 254)
    private String correo;

    @Column(name = "numero_id", nullable = false, precision = 10)
    private BigDecimal numeroId;

    @OneToMany(mappedBy = "idArtista")
    private Set<Estampa> estampas = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idArtista")
    private Set<MedioDePago> medioDePagos = new LinkedHashSet<>();

}