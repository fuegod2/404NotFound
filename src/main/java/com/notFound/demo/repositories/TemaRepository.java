package com.notFound.demo.repositories;

import com.notFound.demo.entities.Tema;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface TemaRepository extends JpaRepository<Tema, Integer> {

    Tema save(Tema tema);

    List<Tema> findAll();
}