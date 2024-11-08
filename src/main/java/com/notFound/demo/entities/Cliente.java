package com.notFound.demo.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "cliente")
public class Cliente {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id", nullable = false)
    private Usuario usuario;

    @Column(name = "cedula", nullable = false, length = 50)
    private String cedula;

    @Column(name = "correo", nullable = false, length = 50)
    private String correo;

    @OneToOne(mappedBy = "id")
    private Direccion direccion;

    @OneToMany(mappedBy = "idOwner")
    private Set<MedioDePago> medioDePagos = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idCliente")
    private Set<Pedido> pedidos = new LinkedHashSet<>();

}