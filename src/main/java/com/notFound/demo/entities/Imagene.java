package com.notFound.demo.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "imagenes")
public class Imagene {
    @EmbeddedId
    private ImageneId id;

    @MapsId("idEstampa")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_estampa", nullable = false)
    private Estampa idEstampa;

    @Column(name = "enlace", nullable = false, length = 200)
    private String enlace;

}