package com.notFound.demo.DTOs;

import com.notFound.demo.entities.Estampa;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

@Getter
@Setter
public class CarritoDTO {



        private String selectedColor;
        private String selectedModel;
        private String selectedFabric;
        private String selectedSize;
        private String selectedType;
        private Integer  idEstampa;
        private double shirtPrice;
        private BigInteger posicion_x;
    private BigInteger posicion_y;
    private int quantity;
    private BigInteger idCliente;

        // Getters and Setter


    }
