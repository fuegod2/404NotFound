package com.notFound.demo.repositories;

import com.notFound.demo.entities.Camisa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CamisaRepository extends JpaRepository<Camisa, Integer> {
    Optional<Camisa> findByTallaAndColorAndMaterialAndTipo(String talla, String color, String material, String tipo);
    Optional< Camisa> findById(Integer id);
}