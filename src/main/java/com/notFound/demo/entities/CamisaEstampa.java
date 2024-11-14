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
@Table(name = "camisa_estampa")
public class CamisaEstampa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_camisa_estampa", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_camisa", nullable = false)
    private Camisa idCamisa;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_estampa", nullable = false)
    private Estampa idEstampa;

    @Column(name = "precio_camisa_estampa", nullable = false, precision = 10, scale = 2)
    private BigDecimal precioCamisaEstampa;

    @OneToMany(mappedBy = "idCamisaEstampa")
    private Set<DetallePedido> detallePedidos = new LinkedHashSet<>();

}