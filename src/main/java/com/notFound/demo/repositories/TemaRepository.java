package com.notFound.demo.repositories;

import com.notFound.demo.entities.Tema;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TemaRepository extends JpaRepository<Tema, Integer> {

    Tema save(Tema tema);

}