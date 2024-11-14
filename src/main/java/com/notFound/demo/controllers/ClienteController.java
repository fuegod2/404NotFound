package com.notFound.demo.controllers;

import com.notFound.demo.entities.Cliente;
import com.notFound.demo.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    ClienteRepository clienteRepository;

    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("/login")
    private boolean login(@RequestParam String usuario, @RequestParam String contrasena) {

        Optional<Cliente> cliente = clienteRepository.findByUsuario(usuario);

        if (cliente.isPresent()) {

            return contrasena.equals(cliente.get().getContrasena());
        }
        return false;
    }


}
