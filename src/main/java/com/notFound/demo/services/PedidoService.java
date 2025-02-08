package com.notFound.demo.services;

import com.notFound.demo.DTOs.CarritoDTO;
import com.notFound.demo.entities.*;
import com.notFound.demo.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class PedidoService {
@Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private EstampaRepository estampaRepository;

@Autowired
    private DetallePedidoRepository detallePedidoRepository;

@Autowired
    private CamisaEstampaService camisaEstampaService;

    @Transactional
    public Pedido calcularValorTotalPedido(Integer idPedido) {
        Pedido pedido = pedidoRepository.findById(idPedido)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));

        BigDecimal valorTotal = BigDecimal.ONE;
        for (DetallePedido detalle : pedido.getDetallePedidos()) {
            valorTotal = valorTotal.add(detalle.getValorItemTotal());
        }

        pedido.setValorTotal(valorTotal);
        return pedidoRepository.save(pedido);
    }
    public Pedido createPedido(List<CarritoDTO> cartItems, Cliente cliente) {
        // Create the order
        Pedido pedido = new Pedido();
        pedido.setId(((int)pedidoRepository.count())+1);
        pedido.setFPedido(LocalDate.now());
        pedido.setValorTotal(BigDecimal.ONE);
        pedido.setIdCliente(cliente);

        pedido.setEstado("PENDIENTE");
        // Initialize total to 0
        pedido = pedidoRepository.save(pedido);

        BigDecimal totalPedido = BigDecimal.ONE;

        // Process each item in the cart
        for (CarritoDTO cartItem : cartItems) {

            // Extraer el idEstampa del objeto stamp
            Integer idEstampa = cartItem.getStamp().getId();

            // Extraer las coordenadas del objeto position
            Integer posicionX = cartItem.getPosition().getX();
            Integer posicionY = cartItem.getPosition().getY();

            // Create or retrieve the CamisaEstampa
            CamisaEstampa camisaEstampaObj = camisaEstampaService.createCamisaEstampa(
                    cartItem.getSelectedSize(),
                    cartItem.getSelectedColor(),
                    cartItem.getSelectedFabric(),
                    cartItem.getSelectedModel(),
                    BigDecimal.valueOf(cartItem.getShirtPrice()),
                    idEstampa,
                    posicionX,
                    posicionY
            );

            // Calculate the total value for this item
            BigDecimal valorItemTotal = CalcularSubtotal(camisaEstampaObj).multiply(BigDecimal.valueOf(cartItem.getQuantity()));

            // Create the DetallePedido
            DetallePedido detallePedido = new DetallePedido();
            detallePedido.setId(((int)detallePedidoRepository.count())+1);
            detallePedido.setCantidad(cartItem.getQuantity());
            detallePedido.setValorItemTotal(valorItemTotal);
            detallePedido.setIdCamisaEstampa(camisaEstampaObj);
            detallePedido.setIdPedido(pedido);

            // Save the DetallePedido
            detallePedidoRepository.save(detallePedido);

            // Update the total order value
            totalPedido = totalPedido.add(valorItemTotal);
        }

        // Update the order total
        pedido.setValorTotal(totalPedido);

        pedido.setEstado("ACEPTADO");

        return pedidoRepository.save(pedido);
    }

    private BigDecimal CalcularSubtotal(CamisaEstampa camisaEstampa) {
       return   camisaEstampa.getIdEstampa().getPrecio().add(camisaEstampa.getIdCamisa().getPrecioCamisa());

    }


    @Transactional
    public boolean procesarPago(){

        return false;
    }
}