package com.notFound.demo.modelo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "medio_de_pago")
public class MedioDePago {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "f_vencimiento", nullable = false)
    private LocalDate fVencimiento;

    @Column(name = "numero", nullable = false, precision = 16)
    private BigDecimal numero;

    @Column(name = "tipo", nullable = false, length = 2)
    private String tipo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_owner")
    private Cliente cliente;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getfVencimiento() {
        return fVencimiento;
    }

    public void setfVencimiento(LocalDate fVencimiento) {
        this.fVencimiento = fVencimiento;
    }

    public BigDecimal getNumero() {
        return numero;
    }

    public void setNumero(BigDecimal numero) {
        this.numero = numero;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Cliente getCliente() {
        return idOwner;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}