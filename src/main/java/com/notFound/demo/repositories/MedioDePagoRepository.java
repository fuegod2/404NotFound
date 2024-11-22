package com.notFound.demo.repositories;

import com.notFound.demo.entities.MedioDePago;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MedioDePagoRepository extends JpaRepository<MedioDePago, Integer> {

    MedioDePago save(MedioDePago medioDePago);

}