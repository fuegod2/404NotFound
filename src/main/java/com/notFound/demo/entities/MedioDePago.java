package com.notFound.demo.entities;

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
    private Artista idOwner;

}