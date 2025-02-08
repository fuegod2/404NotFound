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

    @Column(name = "numero", nullable = false, length = Integer.MAX_VALUE)
    private String numero;

    @Column(name = "calle", nullable = false, length = 10)
    private String calle;

    @Column(name = "carrera", nullable = false, length = 10)
    private String carrera;

    @Column(name = "ciudad", nullable = false, length = 50)
    private String ciudad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente")
    private Cliente idCliente;

}