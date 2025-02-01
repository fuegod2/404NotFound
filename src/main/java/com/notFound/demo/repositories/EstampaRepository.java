package com.notFound.demo.repositories;

import com.notFound.demo.entities.Estampa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EstampaRepository extends JpaRepository<Estampa, Integer> {


    Optional<Estampa> findById(Integer id);

    Estampa save(Estampa estampa);

    long count();


}