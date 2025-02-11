package com.notFound.demo.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "direccion")
public class Direccion {
    @Id
    @Column(name = "id_direccion", nullable = false)
    private Integer id;

    @Column(name = "codigo_postal", nullable = false)
    private Integer codigoPostal;


    @Column(name = "direccion", nullable = false, length = 200)
    private String direccion;

    @Column(name = "detelles_direccion", nullable = false, length = 200)
    private String detellesDireccion;

    @Column(name = "nombre_direccion", nullable = false, length = 200)
    private String nombreDireccion;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente")
    private Cliente idCliente;

}