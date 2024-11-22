package com.notFound.demo.repositories;

import com.notFound.demo.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {


  Optional<Cliente> findByUsuario(String usuario);

  Cliente save(Cliente cliente);

  long count();


}