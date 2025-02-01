package com.notFound.demo.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "imagen")
public class Imagen {
    @Id
    @Column(name = "id_imagen", nullable = false)
    private Integer id;

    @Column(name = "enlace", nullable = false, length = 2000)
    private String enlace;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_estampa")
    private Estampa idEstampa;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEnlace() {
        return enlace;
    }

    public void setEnlace(String enlace) {
        this.enlace = enlace;
    }

    public Estampa getIdEstampa() {
        return idEstampa;
    }

    public void setIdEstampa(Estampa idEstampa) {
        this.idEstampa = idEstampa;
    }
}