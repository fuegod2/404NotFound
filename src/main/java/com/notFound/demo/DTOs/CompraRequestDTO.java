package com.notFound.demo.DTOs;

import java.util.List;

public class CompraRequestDTO {
    private List<CarritoDTO> cart;
    private Integer pago;

    // Getters y Setters
    public List<CarritoDTO> getCart() {
        return cart;
    }

    public void setCart(List<CarritoDTO> cart) {
        this.cart = cart;
    }

    public Integer getPago() {
        return pago;
    }

    public void setPago(Integer pago) {
        this.pago = pago;
    }
}