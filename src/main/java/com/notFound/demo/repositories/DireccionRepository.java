package com.notFound.demo.repositories;

import com.notFound.demo.entities.Cliente;
import com.notFound.demo.entities.Direccion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DireccionRepository extends JpaRepository<Direccion, Integer> {
     List<Direccion> findByidCliente(Cliente clienteObj);
}