package com.notFound.demo.repositories;

import com.notFound.demo.entities.DetallePedido;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface DetallePedidoRepository extends JpaRepository<DetallePedido, Integer> {

        Optional<DetallePedido> findById(Integer id);

        DetallePedido save(DetallePedido estampa);

        long count();


    }