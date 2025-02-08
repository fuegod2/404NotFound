package com.notFound.demo.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarritoDTO {



        private String selectedColor;
        private String selectedModel;
        private String selectedFabric;
        private String selectedSize;
        private EstampaDTO stamp;
        private double shirtPrice;
        private PosicionDTO position;
    private int quantity;

        // Getters and Setter


    }
