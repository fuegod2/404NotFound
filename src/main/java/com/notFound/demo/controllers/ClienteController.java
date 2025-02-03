package com.notFound.demo.controllers;

import com.notFound.demo.entities.Cliente;
import com.notFound.demo.entities.MedioDePago;
import com.notFound.demo.repositories.ClienteRepository;
import com.notFound.demo.repositories.MedioDePagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private MedioDePagoRepository medioDePagoRepository;

    @Autowired
    ClienteRepository clienteRepository;

    private Cliente clienteObj;

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

    @GetMapping("/register")
    public boolean register(@RequestParam String nombre,
                             @RequestParam String apellido,
                             @RequestParam String usuario,
                             @RequestParam String contrasena,
                             @RequestParam String tipo_id,
                             @RequestParam String correo,
                             @RequestParam BigDecimal numero_id,
                             @RequestParam String numero_tarjeta,
                             @RequestParam String tipo_tarjeta,
                             @RequestParam LocalDate f_vencimiento){


        try {
            Cliente clienteObj = new Cliente();
            clienteObj.setId(((int)clienteRepository.count())+1);
            clienteObj.setNombre(nombre);
            clienteObj.setApellido(apellido);
            clienteObj.setUsuario(usuario);
            clienteObj.setContrasena(contrasena);
            clienteObj.setTipoId(tipo_id);
            clienteObj.setCorreo(correo);
            clienteObj.setNumeroId(numero_id);


            clienteRepository.save(clienteObj);

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


}
