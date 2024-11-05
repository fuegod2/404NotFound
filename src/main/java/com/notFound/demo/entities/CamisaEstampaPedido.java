package com.notFound.demo.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "camisa_estampa_pedido")
public class CamisaEstampaPedido {
    @Id
    @Column(name = "id_aux", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_camisa_estampa", nullable = false)
    private CamisetaEstampa idCamisaEstampa;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_pedido", nullable = false)
    private Pedido idPedido;

    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;

}