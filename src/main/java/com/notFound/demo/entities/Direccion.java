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
    @Column(name = "id", nullable = false)
    private Integer id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id", nullable = false)
    private Cliente cliente;

    @Column(name = "codigo_postal", nullable = false, length = 50)
    private String codigoPostal;

    @Column(name = "calle", nullable = false, length = 50)
    private String calle;

    @Column(name = "numero", nullable = false, length = 50)
    private String numero;

    @Column(name = "ciudad", nullable = false, length = 50)
    private String ciudad;

    @Column(name = "carrera", nullable = false, length = 50)
    private String carrera;

}