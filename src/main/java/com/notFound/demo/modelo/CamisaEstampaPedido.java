package com.notFound.demo.modelo;

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


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public CamisetaEstampa getIdCamisaEstampa() {
        return idCamisaEstampa;
    }

    public void setIdCamisaEstampa(CamisetaEstampa idCamisaEstampa) {
        this.idCamisaEstampa = idCamisaEstampa;
    }

    public Pedido getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Pedido idPedido) {
        this.idPedido = idPedido;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
}