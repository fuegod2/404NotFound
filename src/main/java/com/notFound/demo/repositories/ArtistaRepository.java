package com.notFound.demo.repositories;

import com.notFound.demo.entities.Artista;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface ArtistaRepository  extends JpaRepository<Artista, Integer> {


    Optional<Artista> findByUsuario(String usuario);

    Artista save(Artista artista);

    long count();




}