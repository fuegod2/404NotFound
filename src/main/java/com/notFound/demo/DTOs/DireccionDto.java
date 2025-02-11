package com.notFound.demo.DTOs;

import com.notFound.demo.entities.Direccion;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.notFound.demo.entities.Direccion}
 */
@Value
public class DireccionDto implements Serializable {
    Integer codigo_postal;
    String direccion;
    String detalles_direccion;
    String nombre_direccion;
    public DireccionDto(Direccion direccion) {
        this.codigo_postal = direccion.getCodigoPostal();
        this.direccion = direccion.getDireccion();
        this.detalles_direccion = direccion.getDetellesDireccion();
        this.nombre_direccion = direccion.getNombreDireccion();

    }
}