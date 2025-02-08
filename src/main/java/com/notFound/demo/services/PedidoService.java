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
    private MedioDePagoRepository medioDePagoRepository;
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
    @Transactional
    public boolean createPedido(List<CarritoDTO> cartItems, Cliente cliente) {
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
            pedido.agregarDetallePedido(detallePedido);
            // Update the total order value
            totalPedido = totalPedido.add(valorItemTotal);
        }
        pedido.setValorTotal(totalPedido.subtract(BigDecimal.ONE));
        pedidoRepository.save(pedido);
        // Update the order total

        if (procesarPago(pedido)){
            pedido.setEstado("ACEPTADO");
            reducirStockDeEstampas(pedido);

            return true;
        }
        else{
            throw new RuntimeException("Algo salió mal :(");
        }



    }

    @Transactional
    public void reducirStockDeEstampas(Pedido pedido) {
        // Recorrer los detalles del pedido
        for (DetallePedido detalle : pedido.getDetallePedidos()) {
            // Obtener la CamisaEstampa asociada al detalle
            CamisaEstampa camisaEstampa = detalle.getIdCamisaEstampa();

            // Obtener la Estampa asociada a la CamisaEstampa
            Estampa estampa = camisaEstampa.getIdEstampa();

            // Reducir el stock de la estampa según la cantidad ordenada
            int nuevoStock = estampa.getStock() - detalle.getCantidad();
            if (nuevoStock < 0) {
                throw new RuntimeException("Stock insuficiente para la estampa con ID: " + estampa.getId());
            }

            // Actualizar el stock de la estampa
            estampa.setStock(nuevoStock);
            estampaRepository.save(estampa);
        }
    }

    private BigDecimal CalcularSubtotal(CamisaEstampa camisaEstampa) {
       return   camisaEstampa.getIdEstampa().getPrecio().add(camisaEstampa.getIdCamisa().getPrecioCamisa());

    }


    @Transactional
    public boolean procesarPago(Pedido pedido){
        try {


            Cliente cliente = pedido.getIdCliente();

            // Obtener el medio de pago del cliente
            MedioDePago medioDePagoObj = cliente.getMedioDePagos().stream()
                    .findFirst() // Obtener el primer medio de pago (o el que corresponda)
                    .orElse(null);

            // Verificar si el cliente tiene un medio de pago válido
            if (medioDePagoObj == null) {
                System.out.println("El cliente no tiene un medio de pago registrado.");
                return false;
            }

            // Obtener el saldo del medio de pago
            BigDecimal saldo = medioDePagoObj.getSaldo();

            // Obtener el costo total del pedido
            BigDecimal costoPedido = pedido.getValorTotal();

            // Verificar si el saldo es suficiente
            if (saldo.compareTo(costoPedido) >= 0) {
                // Descontar el costo del pedido del saldo
                BigDecimal nuevoSaldo = saldo.subtract(costoPedido);
                medioDePagoObj.setSaldo(nuevoSaldo);

                // Guardar el medio de pago actualizado (si es necesario)
                medioDePagoRepository.save(medioDePagoObj);

                System.out.println("Pago procesado exitosamente. Nuevo saldo: " + nuevoSaldo);
                return true;
            } else {
                System.out.println("Saldo insuficiente. Saldo actual: " + saldo + ", Costo del pedido: " + costoPedido);
                return false;
            }
        } catch (Exception e){
            return false;
        }
    }
}