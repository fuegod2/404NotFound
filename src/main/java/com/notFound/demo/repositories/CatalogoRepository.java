package com.notFound.demo.repositories;

import com.notFound.demo.entities.Catalogo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CatalogoRepository extends JpaRepository<Catalogo, Integer> {

  List<Catalogo> findAll();
}