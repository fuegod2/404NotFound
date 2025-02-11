package com.notFound.demo.DTOs;

import com.notFound.demo.entities.Cliente;
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

    public ClienteDTO(Cliente clienteObj) {
        this.nombre = clienteObj.getNombre();
        this.apellido = clienteObj.getApellido();
        this.usuario = clienteObj.getUsuario();
        this.tipoId = clienteObj.getTipoId();
        this.correo = clienteObj.getCorreo();
        this.numeroId = clienteObj.getNumeroId();
        this.idCliente = clienteObj.getId();

    }
}