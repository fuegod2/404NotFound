package com.notFound.demo.controllers;

import com.notFound.demo.DTOs.CarritoDTO;
import com.notFound.demo.entities.Pedido;
import com.notFound.demo.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

@Autowired
  private PedidoService pedidoService;

    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/checkout")
    public Pedido checkout(@RequestBody List<CarritoDTO> cartItems) {

        return pedidoService.createPedido(cartItems);
    }
}