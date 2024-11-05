package com.notFound.demo.modelo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "camiseta_estampa")
public class CamisetaEstampa {
    @Id
    @Column(name = "\"ID\"", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_camiseta", nullable = false)
    private Camiseta idCamiseta;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_estampa", nullable = false)
    private Estampa idEstampa;

    @Column(name = "posicion_x", nullable = false)
    private Integer posicionX;

    @Column(name = "posicion_y", nullable = false)
    private Integer posicionY;

    @Column(name = "estado_config", nullable = false, length = 2)
    private String estadoConfig;

    @OneToMany(mappedBy = "idCamisaEstampa")
    private Set<CamisaEstampaPedido> camisaEstampaPedidos = new LinkedHashSet<>();


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Camiseta getIdCamiseta() {
        return idCamiseta;
    }

    public void setIdCamiseta(Camiseta idCamiseta) {
        this.idCamiseta = idCamiseta;
    }

    public Estampa getIdEstampa() {
        return idEstampa;
    }

    public void setIdEstampa(Estampa idEstampa) {
        this.idEstampa = idEstampa;
    }

    public Integer getPosicionX() {
        return posicionX;
    }

    public void setPosicionX(Integer posicionX) {
        this.posicionX = posicionX;
    }

    public Integer getPosicionY() {
        return posicionY;
    }

    public void setPosicionY(Integer posicionY) {
        this.posicionY = posicionY;
    }

    public String getEstadoConfig() {
        return estadoConfig;
    }

    public void setEstadoConfig(String estadoConfig) {
        this.estadoConfig = estadoConfig;
    }

    public Set<CamisaEstampaPedido> getCamisaEstampaPedidos() {
        return camisaEstampaPedidos;
    }

    public void setCamisaEstampaPedidos(Set<CamisaEstampaPedido> camisaEstampaPedidos) {
        this.camisaEstampaPedidos = camisaEstampaPedidos;
    }
}