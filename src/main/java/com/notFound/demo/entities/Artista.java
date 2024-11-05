package com.notFound.demo.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "artista")
public class Artista {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id", nullable = false)
    private Usuario usuario;

    @OneToMany(mappedBy = "idArtista")
    private Set<Estampa> estampas = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idOwner")
    private Set<MedioDePago> medioDePagos = new LinkedHashSet<>();

}