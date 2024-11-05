package com.notFound.demo.modelo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "cliente")
public class Cliente {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id", nullable = false)
    private Usuario usuario;

    @Column(name = "cedula", nullable = false, length = 50)
    private String cedula;

    @Column(name = "correo", nullable = false, length = 50)
    private String correo;

    @OneToOne(mappedBy = "cliente")
    private Direccion direccion;

    @OneToMany(mappedBy = "idOwner")
    private Set<MedioDePago> medioDePagos = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idCliente")
    private Set<Pedido> pedidos = new LinkedHashSet<>();


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public Set<MedioDePago> getMedioDePagos() {
        return medioDePagos;
    }

    public void setMedioDePagos(Set<MedioDePago> medioDePagos) {
        this.medioDePagos = medioDePagos;
    }

    public Set<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(Set<Pedido> pedidos) {
        this.pedidos = pedidos;
    }
}