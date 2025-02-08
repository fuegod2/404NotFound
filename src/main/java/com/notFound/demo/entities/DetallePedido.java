package com.notFound.demo.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "detalle_pedido")
public class DetallePedido {
    @Id
    @Column(name = "id_detalle_pedido", nullable = false)
    private Integer id;

    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;

    @Column(name = "valor_item_total", precision = 10, scale = 2)
    private BigDecimal valorItemTotal;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_camisa_estampa", nullable = false)
    private CamisaEstampa idCamisaEstampa;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_pedido", nullable = false)
    private Pedido idPedido;

}