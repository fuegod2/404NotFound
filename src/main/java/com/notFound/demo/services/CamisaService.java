package com.notFound.demo.services;

import com.notFound.demo.entities.Camisa;
import com.notFound.demo.repositories.CamisaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
@Service
public class CamisaService
{
    @Autowired
private CamisaRepository camisaRepository;

    @Transactional
    public Camisa findCamisa(String talla, String color, String material, String tipo) {
        // Check if the shirt already exists
        Optional<Camisa> existingCamisa = camisaRepository.findByTallaAndColorAndMaterialAndTipo(
                talla, color, material, tipo);
        return existingCamisa.orElse(null);
    }
}
