package com.notFound.demo.controllers;

import com.notFound.demo.DTOs.*;
import com.notFound.demo.entities.Cliente;
import com.notFound.demo.entities.Direccion;
import com.notFound.demo.repositories.DireccionRepository;
import com.notFound.demo.entities.MedioDePago;
import com.notFound.demo.repositories.ClienteRepository;
import com.notFound.demo.repositories.MedioDePagoRepository;
import com.notFound.demo.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private MedioDePagoRepository medioDePagoRepository;

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    PedidoService pedidoService;

    private Cliente clienteObj;
    @Autowired
    private DireccionRepository direccionRepository;

    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("/login")
    public ResponseEntity<Integer> login(
            @RequestParam String usuario,
            @RequestParam String contrasena
    ) {
        Optional<Cliente> cliente = clienteRepository.findByUsuario(usuario);

        if (cliente.isPresent() && contrasena.equals(cliente.get().getContrasena())) {

            clienteObj = cliente.get();
            System.out.println(cliente.get().getId());
            return ResponseEntity.ok(cliente.get().getId()); // Retorna ID con status 200

        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); // Credenciales inválidas
    }
    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("/register")
    public boolean register(@RequestParam String nombre,
                            @RequestParam String apellido,
                            @RequestParam String usuario,
                            @RequestParam String contrasena,
                            @RequestParam String tipo_id,
                            @RequestParam String correo,
                            @RequestParam String numero_id) {


        try {
           // System.out.println(registro.getApellido());
            Cliente clienteObj = new Cliente();
            clienteObj.setId(((int)clienteRepository.count())+10001);
            clienteObj.setNombre(nombre);
            clienteObj.setApellido(apellido);
            clienteObj.setUsuario(usuario);
            clienteObj.setContrasena(contrasena);
            clienteObj.setTipoId(tipo_id);
            clienteObj.setCorreo(correo);
            BigDecimal numeroId = new BigDecimal(numero_id);
            clienteObj.setNumeroId(numeroId);


            clienteRepository.save(clienteObj);
            this.clienteObj = clienteObj;


            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

    }



    @Transactional
    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("/registerMedioDireccion")
    public boolean registerMedio(

                                 @RequestParam String numeroTarjeta,
                                 @RequestParam String tipoTarjeta,
                                 @RequestParam String fechaVencimiento,
                                 @RequestParam Integer codigoPostal,
                                 @RequestParam String nombreDireccion,
                                 @RequestParam String direccion,
                                 @RequestParam String detalles
                                  ){
        try {

            MedioDePago medioDePagoObj = new MedioDePago();
            medioDePagoObj.setId(((int)medioDePagoRepository.count())+1);
            medioDePagoObj.setNumeroTarjeta(numeroTarjeta);
            medioDePagoObj.setTipoTarjeta(tipoTarjeta);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yyyy");

            // Convierte el String a YearMonth primero
            YearMonth yearMonth = YearMonth.parse(fechaVencimiento, formatter);

            // Convierte YearMonth a LocalDate (asignando el primer día del mes)
            medioDePagoObj.setfVencimiento(yearMonth.atDay(1));
            medioDePagoObj.setIdCliente(clienteObj);
            medioDePagoObj.setSaldo(BigDecimal.valueOf(500000.00));
            medioDePagoRepository.save(medioDePagoObj);
            Direccion direccionObj = new Direccion();
            direccionObj.setIdCliente(clienteObj);
            direccionObj.setDireccion(direccion);
            direccionObj.setId(((int)direccionRepository.count())+1);
            direccionObj.setDetellesDireccion(detalles);
            direccionObj.setNombreDireccion(nombreDireccion);
            direccionObj.setCodigoPostal(codigoPostal);
            direccionRepository.save(direccionObj);
            return true;


        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    @Transactional
    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("/registerMedio")
    public boolean registerMedioPago(

            @RequestParam String numero_tarjeta,
            @RequestParam String tipo_tarjeta,
            @RequestParam LocalDate f_vencimiento
    ){
        try {
            MedioDePago medioDePagoObj = new MedioDePago();
            medioDePagoObj.setId(((int)medioDePagoRepository.count())+1);
            medioDePagoObj.setNumeroTarjeta(numero_tarjeta);
            medioDePagoObj.setTipoTarjeta(tipo_tarjeta);
            medioDePagoObj.setfVencimiento(f_vencimiento);
            medioDePagoObj.setIdCliente(clienteObj);
            medioDePagoRepository.save(medioDePagoObj);
            return true;


        } catch (Exception e) {
            return false;
        }

    }

    @Transactional
    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("/editarMedio")
    public boolean editarMedioPago(

            @RequestParam String numero_tarjeta,
            @RequestParam String tipo_tarjeta,
            @RequestParam LocalDate f_vencimiento,
            @RequestParam Integer id
    ){
        try {
            MedioDePago medioDePagoObj = medioDePagoRepository.findById(id).get();
            medioDePagoObj.setNumeroTarjeta(numero_tarjeta);
            medioDePagoObj.setTipoTarjeta(tipo_tarjeta);
            medioDePagoObj.setfVencimiento(f_vencimiento);
            medioDePagoRepository.save(medioDePagoObj);
            return true;


        } catch (Exception e) {
            return false;
        }

    }

    @Transactional
    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("/editarDir")
    public boolean editarDireccion(DireccionDto direccion

    ){
        try {
            Direccion direccionObj = direccionRepository.findByidCliente(this.clienteObj).get(0);
            direccionObj.setNombreDireccion(direccion.getNombre_direccion());
            direccionObj.setCodigoPostal(direccion.getCodigo_postal());
            direccionObj.setDetellesDireccion(direccion.getDetalles_direccion());
            direccionRepository.save(direccionObj);
            return true;


        } catch (Exception e) {
            return false;
        }

    }


    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping ("/comprarPedido")
    public String comprarPedido(@RequestBody CompraRequestDTO compra) {

        return pedidoService.createPedido(compra, this.clienteObj);

    }

    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("/mediosPago")
    public  ResponseEntity<List<MedioDePagoDto>> mediosDePago() {
        List<MedioDePago> medios = medioDePagoRepository.findByidCliente(this.clienteObj);
        List<MedioDePagoDto> medioDePagoDtos = medios.stream()
                .map(MedioDePagoDto::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(medioDePagoDtos);
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping ("/direcciones")
    public  ResponseEntity<DireccionDto>Direcciones() {
        List<Direccion> direcciones = direccionRepository.findByidCliente(this.clienteObj);


        DireccionDto direccionDto = new DireccionDto(direcciones.get(0));
        return ResponseEntity.ok(direccionDto);
    }
    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping ("/infoCliente")
    public  ResponseEntity<ClienteDTO> infoCliente() {
        ClienteDTO clientelogged = new ClienteDTO(this.clienteObj);
        return  ResponseEntity.ok(clientelogged);
    }

}
