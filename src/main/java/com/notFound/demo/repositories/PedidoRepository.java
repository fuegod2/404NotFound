package com.notFound.demo.repositories;

import com.notFound.demo.entities.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {


        Optional<Pedido> findById(Integer id);

        Pedido save(Pedido pedido);

        long count();


    }