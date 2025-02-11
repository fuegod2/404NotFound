package com.notFound.demo.repositories;

import com.notFound.demo.entities.Tema;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface TemaRepository extends JpaRepository<Tema, Integer> {
    Optional<Tema> findByNombre(String nombre);

    Tema save(Tema tema);

    List<Tema> findAll();
}