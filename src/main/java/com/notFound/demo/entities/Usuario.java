package com.notFound.demo.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "usuario", nullable = false, length = 50)
    private String usuario;

    @Column(name = "password", nullable = false, length = 50)
    private String password;

    @OneToOne(mappedBy = "id")
    private Artista artista;

    @OneToOne(mappedBy = "id")
    private Cliente cliente;

}