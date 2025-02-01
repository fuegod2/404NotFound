package com.notFound.demo.repositories;

import com.notFound.demo.entities.Imagen;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ImagenRepository extends JpaRepository<Imagen, Integer> {

    Imagen save(Imagen imagen);
    long count();

}