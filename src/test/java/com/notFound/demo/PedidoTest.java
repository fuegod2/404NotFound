package com.notFound.demo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.math.BigDecimal;
import java.util.Optional;
import java.util.Set;

import com.notFound.demo.entities.DetallePedido;
import com.notFound.demo.entities.Pedido;
import com.notFound.demo.repositories.PedidoRepository;
import com.notFound.demo.services.PedidoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PedidoTest {

    @Mock
    private PedidoRepository pedidoRepository;

    @InjectMocks
    private PedidoService pedidoService;

    @Test
    void calcularValorTotalPedido_ConDetalles_ActualizaValorTotal() {
        // Configurar mocks
        Pedido pedidoMock = new Pedido();
        pedidoMock.setId(1);

        DetallePedido detalle1 = new DetallePedido();
        detalle1.setValorItemTotal(new BigDecimal("40000"));

        DetallePedido detalle2 = new DetallePedido();
        detalle2.setValorItemTotal(new BigDecimal("50000"));

        pedidoMock.setDetallePedidos(Set.of(detalle1, detalle2));

        when(pedidoRepository.findById(1)).thenReturn(Optional.of(pedidoMock));
        when(pedidoRepository.save(any(Pedido.class))).thenReturn(pedidoMock);

        // Ejecutar método
        Pedido resultado = pedidoService.calcularValorTotalPedido(1);

        // Verificar
        assertEquals(new BigDecimal("90000"), resultado.getValorTotal());
        verify(pedidoRepository).save(pedidoMock);
    }

    @Test
    void calcularValorTotalPedido_SinDetalles_ValorTotalCero() {
        Pedido pedidoMock = new Pedido();
        pedidoMock.setId(1);
        pedidoMock.setDetallePedidos(Set.of()); // Lista vacía

        when(pedidoRepository.findById(1)).thenReturn(Optional.of(pedidoMock));
        when(pedidoRepository.save(any(Pedido.class))).thenReturn(pedidoMock);

        Pedido resultado = pedidoService.calcularValorTotalPedido(1);

        assertEquals(BigDecimal.ZERO, resultado.getValorTotal());
    }

    @Test
    void calcularValorTotalPedido_PedidoNoExiste_LanzaExcepcion() {
        when(pedidoRepository.findById(99)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            pedidoService.calcularValorTotalPedido(99);
        });
    }
}