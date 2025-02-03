package com.notFound.demo.services;

import com.notFound.demo.entities.DetallePedido;
import com.notFound.demo.entities.Pedido;
import com.notFound.demo.repositories.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;

@Service
public class PedidoService {
@Autowired
    private PedidoRepository pedidoRepository;

    @Transactional
    public Pedido calcularValorTotalPedido(Integer idPedido) {
        Pedido pedido = pedidoRepository.findById(idPedido)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));

        BigDecimal valorTotal = BigDecimal.ZERO;
        for (DetallePedido detalle : pedido.getDetallePedidos()) {
            valorTotal = valorTotal.add(detalle.getValorItemTotal());
        }

        pedido.setValorTotal(valorTotal);
        return pedidoRepository.save(pedido);
    }
}