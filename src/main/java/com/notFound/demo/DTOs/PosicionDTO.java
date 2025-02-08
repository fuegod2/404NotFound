package com.notFound.demo.DTOs;

import lombok.Value;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

@Value
public class PosicionDTO implements Serializable {
    private Integer x; // posicion_x
    private Integer y; // posicion_y

}
