package com.notFound.demo.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "camiseta_estampa")
public class CamisetaEstampa {
    @Id
    @Column(name = "\"ID\"", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_camiseta", nullable = false)
    private Camiseta idCamiseta;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_estampa", nullable = false)
    private Estampa idEstampa;

    @Column(name = "posicion_x", nullable = false)
    private Integer posicionX;

    @Column(name = "posicion_y", nullable = false)
    private Integer posicionY;

    @Column(name = "estado_config", nullable = false, length = 2)
    private String estadoConfig;

    @OneToMany(mappedBy = "idCamisaEstampa")
    private Set<CamisaEstampaPedido> camisaEstampaPedidos = new LinkedHashSet<>();

}