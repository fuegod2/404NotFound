package com.notFound.demo.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "administrador")
public class Administrador {
    @Id

    @Column(name = "id_administrador", nullable = false)
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

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

}