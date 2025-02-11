package com.notFound.demo.repositories;

import com.notFound.demo.DTOs.MedioDePagoDto;
import com.notFound.demo.entities.Cliente;
import com.notFound.demo.entities.MedioDePago;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface MedioDePagoRepository extends JpaRepository<MedioDePago, Integer> {

    MedioDePago save(MedioDePago medioDePago);

    List<MedioDePago> findByidCliente(Cliente cliente);

}