package com.notFound.demo.entities;

import lombok.Value;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link Cliente}
 */
@Value
public class ClienteDTO implements Serializable {
    Integer idCliente;
    String nombre;
    String apellido;
    String usuario;
    String tipoId;
    String correo;
    BigDecimal numeroId;
}