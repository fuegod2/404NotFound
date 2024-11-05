package com.notFound.demo.modelo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "camiseta")
public class Camiseta {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "talla", nullable = false, length = Integer.MAX_VALUE)
    private String talla;

    @Column(name = "color", nullable = false, length = 20)
    private String color;

    @Column(name = "material", nullable = false, length = 50)
    private String material;

    @Column(name = "tipo", nullable = false, length = 2)
    private String tipo;

    @Column(name = "precio", nullable = false)
    private Integer precio;

    @Column(name = "stock")
    private Integer stock;

    @OneToMany(mappedBy = "idCamiseta")
    private Set<CamisetaEstampa> camisetaEstampas = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTalla() {
        return talla;
    }

    public void setTalla(String talla) {
        this.talla = talla;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getPrecio() {
        return precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Set<CamisetaEstampa> getCamisetaEstampas() {
        return camisetaEstampas;
    }

    public void setCamisetaEstampas(Set<CamisetaEstampa> camisetaEstampas) {
        this.camisetaEstampas = camisetaEstampas;
    }
}