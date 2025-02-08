package com.notFound.demo.services;

import com.notFound.demo.entities.Camisa;
import com.notFound.demo.entities.CamisaEstampa;
import com.notFound.demo.entities.Estampa;
import com.notFound.demo.repositories.CamisaEstampaRepository;
import com.notFound.demo.repositories.EstampaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.BigInteger;

@Service
public class CamisaEstampaService {
    @Autowired
    EstampaRepository estampaRepository;
    @Autowired
    CamisaEstampaRepository camisaEstampaRepository;
   @Autowired
    CamisaService camisaService;

    @Transactional
    public CamisaEstampa createCamisaEstampa(
            String talla, String color, String material, String tipo,
             BigDecimal precioCamisaEstampa,Integer idEstampa, Integer posicionX, Integer posicionY) {

        // Find the shirt
        Camisa camisa = camisaService.findCamisa(talla, color, material, tipo);
        if (camisa == null)  {
            throw new RuntimeException("Camisa no encontrada");
        }

        // Fetch the stamp

        Estampa estampa = estampaRepository.findById(idEstampa)
                .orElseThrow(() -> new RuntimeException("Stamp not found with id: " + idEstampa));

        // Create the CamisaEstampa entity
        CamisaEstampa camisaEstampaObj = new CamisaEstampa();
        camisaEstampaObj.setId(((int)camisaEstampaRepository.count())+1);
        camisaEstampaObj.setIdCamisa(camisa);
        camisaEstampaObj.setIdEstampa(estampa);
        camisaEstampaObj.setPrecioCamisaEstampa(precioCamisaEstampa);
        camisaEstampaObj.setPosicionX((BigInteger.valueOf(posicionX))); // Set position X
        camisaEstampaObj.setPosicionY((BigInteger.valueOf(posicionX))); // Set position Y

        return camisaEstampaRepository.save(camisaEstampaObj);
    }
}
