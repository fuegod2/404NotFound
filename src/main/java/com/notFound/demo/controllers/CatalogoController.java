package com.notFound.demo.controllers;

import com.notFound.demo.DTOs.TemaDTO;
import com.notFound.demo.entities.Catalogo;
import com.notFound.demo.entities.Tema;
import com.notFound.demo.repositories.CatalogoRepository;
//import com.notFound.demo.repositories.EstampaRepository;
import com.notFound.demo.repositories.TemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/")
public class CatalogoController {

    @Autowired
    private TemaRepository temaRepository;
    @Autowired
    private CatalogoRepository catalogoRepository;



    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("/")
    public ResponseEntity<List<TemaDTO>> inicio() {
        List<Tema> temas = temaRepository.findAll();
        List<TemaDTO> temaDTOs = temas.stream()
                .map(TemaDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(temaDTOs);
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("/catalogo")
    public ResponseEntity<List<Catalogo>> catalogo() {
        List<Catalogo> catalogo = catalogoRepository.findAll();

        return ResponseEntity.ok(catalogo);
    }


}