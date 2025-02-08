package com.notFound.demo.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "cliente")
public class Cliente {
    @Id
    @Column(name = "id_cliente", nullable = false)
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "apellido", nullable = false, length = 50)
    private String apellido;

    @Column(name = "usuario", nullable = false, length = 50)
    private String usuario;

    @Column(name = "contrasena", nullable = false, length = 50)
    private String contrasena;

    @Column(name = "tipo_id", nullable = false, length = 2)
    private String tipoId;

    @Column(name = "correo", nullable = false, length = 254)
    private String correo;

    @Column(name = "numero_id", nullable = false, precision = 10)
    private BigDecimal numeroId;

    @OneToMany(mappedBy = "idCliente")
    private Set<Direccion> direccions = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idCliente")
    private Set<MedioDePago> medioDePagos = new LinkedHashSet<>();
/*
    @OneToMany(mappedBy = "idCliente")
    private Set<Pedido> pedidos = new LinkedHashSet<>();
*/
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getTipoId() {
        return tipoId;
    }

    public void setTipoId(String tipoId) {
        this.tipoId = tipoId;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public BigDecimal getNumeroId() {
        return numeroId;
    }

    public void setNumeroId(BigDecimal numeroId) {
        this.numeroId = numeroId;
    }

    public Set<Direccion> getDireccions() {
        return direccions;
    }

    public void setDireccions(Set<Direccion> direccions) {
        this.direccions = direccions;
    }

    public Set<MedioDePago> getMedioDePagos() {
        return medioDePagos;
    }

    public void setMedioDePagos(Set<MedioDePago> medioDePagos) {
        this.medioDePagos = medioDePagos;
    }
/*
    public Set<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(Set<Pedido> pedidos) {
        this.pedidos = pedidos;
    }
    */
}