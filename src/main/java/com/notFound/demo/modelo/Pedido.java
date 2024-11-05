package com.notFound.demo.modelo;

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


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getTotalPagar() {
        return totalPagar;
    }

    public void setTotalPagar(Integer totalPagar) {
        this.totalPagar = totalPagar;
    }

    public LocalDate getfEntrega() {
        return fEntrega;
    }

    public void setfEntrega(LocalDate fEntrega) {
        this.fEntrega = fEntrega;
    }

    public Cliente getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Cliente idCliente) {
        this.idCliente = idCliente;
    }

    public LocalDate getfPedido() {
        return fPedido;
    }

    public void setfPedido(LocalDate fPedido) {
        this.fPedido = fPedido;
    }

    public Set<CamisaEstampaPedido> getCamisaEstampaPedidos() {
        return camisaEstampaPedidos;
    }

    public void setCamisaEstampaPedidos(Set<CamisaEstampaPedido> camisaEstampaPedidos) {
        this.camisaEstampaPedidos = camisaEstampaPedidos;
    }
}