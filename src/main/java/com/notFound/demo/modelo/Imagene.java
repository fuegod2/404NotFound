package com.notFound.demo.modelo;

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


    public ImageneId getId() {
        return id;
    }

    public void setId(ImageneId id) {
        this.id = id;
    }

    public Estampa getIdEstampa() {
        return idEstampa;
    }

    public void setIdEstampa(Estampa idEstampa) {
        this.idEstampa = idEstampa;
    }

    public String getEnlace() {
        return enlace;
    }

    public void setEnlace(String enlace) {
        this.enlace = enlace;
    }
}