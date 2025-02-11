package com.notFound.demo.DTOs;

import com.notFound.demo.entities.MedioDePago;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link com.notFound.demo.entities.MedioDePago}
 */
@Value
public class MedioDePagoDto implements Serializable {
    Integer id;
    String numeroTarjeta;
    String tipoTarjeta;
   LocalDate fechaVencimiento;
    public MedioDePagoDto(MedioDePago medioDePago) {
        id = medioDePago.getId();

        StringBuilder sb = new StringBuilder(medioDePago.getNumeroTarjeta());
        for (int i = 0; i < 12; i++) {
            sb.setCharAt(i, '*'); // Reemplazar cada carácter con '0'
        }
        numeroTarjeta = sb.toString();
        tipoTarjeta = medioDePago.getTipoTarjeta();
        fechaVencimiento = medioDePago.getfVencimiento();
    }
}