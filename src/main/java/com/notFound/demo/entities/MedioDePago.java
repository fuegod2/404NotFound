package com.notFound.demo.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "medio_de_pago")
public class MedioDePago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_medio_de_pago", nullable = false)
    private Integer id;

    @Column(name = "numero_tarjeta", nullable = false, length = 50)
    private String numeroTarjeta;

    @Column(name = "tipo_tarjeta", nullable = false, length = 25)
    private String tipoTarjeta;

    @Column(name = "f_vencimiento", nullable = false)
    private LocalDate fVencimiento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente")
    private Cliente idCliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_artista")
    private Artista idArtista;

}