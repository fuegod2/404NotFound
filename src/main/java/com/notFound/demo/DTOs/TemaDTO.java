package com.notFound.demo.DTOs;

import com.notFound.demo.entities.Tema;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.notFound.demo.entities.Tema}
 */
@Value
public class TemaDTO implements Serializable {
    Integer id;
    String nombre;
    public TemaDTO(Tema tema) {
        this.id = tema.getId();
        this.nombre = tema.getNombre();
    }

    }
