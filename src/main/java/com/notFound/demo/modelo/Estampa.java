package com.notFound.demo.modelo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "estampa")
public class Estampa {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "precio", nullable = false)
    private Integer precio;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "descripcion", nullable = false, length = 50)
    private String descripcion;

    @Column(name = "visibilidad", nullable = false)
    private Boolean visibilidad = false;

    @Column(name = "stock", nullable = false, length = 50)
    private String stock;

    @Column(name = "calificacion", nullable = false, precision = 2, scale = 1)
    private BigDecimal calificacion;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_tema", nullable = false)
    private Tema idTema;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_artista", nullable = false)
    private Artista idArtista;

    @OneToMany(mappedBy = "idEstampa")
    private Set<CamisetaEstampa> camisetaEstampas = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idEstampa")
    private Set<Imagene> imagenes = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPrecio() {
        return precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Boolean getVisibilidad() {
        return visibilidad;
    }

    public void setVisibilidad(Boolean visibilidad) {
        this.visibilidad = visibilidad;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public BigDecimal getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(BigDecimal calificacion) {
        this.calificacion = calificacion;
    }

    public Tema getIdTema() {
        return idTema;
    }

    public void setIdTema(Tema idTema) {
        this.idTema = idTema;
    }

    public Artista getIdArtista() {
        return idArtista;
    }

    public void setIdArtista(Artista idArtista) {
        this.idArtista = idArtista;
    }

    public Set<CamisetaEstampa> getCamisetaEstampas() {
        return camisetaEstampas;
    }

    public void setCamisetaEstampas(Set<CamisetaEstampa> camisetaEstampas) {
        this.camisetaEstampas = camisetaEstampas;
    }

    public Set<Imagene> getImagenes() {
        return imagenes;
    }

    public void setImagenes(Set<Imagene> imagenes) {
        this.imagenes = imagenes;
    }
}