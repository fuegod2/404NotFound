package com.notFound.demo.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "pedido")
public class Pedido {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "estado", nullable = false, length = 2)
    private String estado;

    @Column(name = "total_pagar", nullable = false)
    private Integer totalPagar;

    @Column(name = "f_entrega")
    private LocalDate fEntrega;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente")
    private Cliente idCliente;

    @Column(name = "f_pedido")
    private LocalDate fPedido;

    @OneToMany(mappedBy = "idPedido")
    private Set<CamisaEstampaPedido> camisaEstampaPedidos = new LinkedHashSet<>();

}