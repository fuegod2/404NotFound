package com.notFound.demo.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "pedido")
public class Pedido {
    @Id

    @Column(name = "id_pedido", nullable = false)
    private Integer id;

    @Column(name = "estado", nullable = false, length = 25)
    private String estado;

    @Column(name = "f_pedido", nullable = false)
    private LocalDate fPedido;

    @Column(name = "valor_total", precision = 10, scale = 2)
    private BigDecimal valorTotal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente")
    private Cliente idCliente;

    @OneToMany(mappedBy = "idPedido")
    private Set<DetallePedido> detallePedidos = new LinkedHashSet<>();

    public void agregarDetallePedido(DetallePedido detallePedido) {
        detallePedidos.add(detallePedido); // Agregar a la colección

    }
}