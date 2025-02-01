package com.notFound.demo.controllers;

import com.notFound.demo.entities.*;
import com.notFound.demo.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;



@RestController
@RequestMapping("/Artistas")
public class ArtistaController {

    @Autowired
    ArtistaRepository artistaRepository;

    @Autowired
    private MedioDePagoRepository medioDePagoRepository;

    @Autowired
    private EstampaRepository estampaRepository;

    @Autowired
    private ImagenRepository imagenRepository;

    @Autowired
    private TemaRepository temaRepository;

    private Artista artistaObj;

    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("/login")
    private boolean login(@RequestParam String usuario, @RequestParam String contrasena) {

        Optional<Artista> artista = artistaRepository.findByUsuario(usuario);

        if (artista.isPresent() && artista.get().getContrasena().equals(contrasena)) {

            artistaObj = artista.get();
            return true;
        }
        return false;
    }

    @GetMapping("/register")
    private boolean register(@RequestParam String nombre,
                             @RequestParam String apellido,
                             @RequestParam String usuario,
                             @RequestParam String contrasena,
                             @RequestParam String tipo_id,
                             @RequestParam String correo,
                             @RequestParam BigDecimal numero_id,
                             @RequestParam String numero_tarjeta,
                             @RequestParam String tipo_tarjeta,
                             @RequestParam LocalDate f_vencimiento){


        try {
            Artista artistaObj = new Artista();
            artistaObj.setId(((int)artistaRepository.count())+1);
            artistaObj.setNombre(nombre);
            artistaObj.setApellido(apellido);
            artistaObj.setUsuario(usuario);
            artistaObj.setContrasena(contrasena);
            artistaObj.setTipoId(tipo_id);
            artistaObj.setCorreo(correo);
            artistaObj.setNumeroId(numero_id);


            artistaRepository.save(artistaObj);

            MedioDePago medioDePagoObj = new MedioDePago();
            medioDePagoObj.setId(((int)medioDePagoRepository.count())+1);
            medioDePagoObj.setNumeroTarjeta(numero_tarjeta);
            medioDePagoObj.setTipoTarjeta(tipo_tarjeta);
            medioDePagoObj.setfVencimiento(f_vencimiento);
            medioDePagoObj.setIdArtista(artistaObj);

            medioDePagoRepository.save(medioDePagoObj);
            return true;
        } catch (Exception e) {
            return false;
        }

    }


    @GetMapping("/uploadEstampa")
    private boolean uploadEstampa(@RequestParam String nombreEstampa,
                                  @RequestParam String descripcionEstampa,
                                  @RequestParam Boolean visibilidadEstampa,
                                  @RequestParam Integer stockEstampa,
                                  @RequestParam BigDecimal calificacionEstampa,
                                  @RequestParam BigDecimal precioEstampa,
                                  @RequestParam String enlaceImagen,
                                  @RequestParam String nombreTema){

        try {

            Tema temaObj = new Tema();
            temaObj.setId(((int)temaRepository.count())+1);
            temaObj.setNombre(nombreTema);

            Estampa estampaObj = new Estampa();
            estampaObj.setId(((int)estampaRepository.count())+1);
            estampaObj.setNombre(nombreEstampa);
            estampaObj.setDescripcion(descripcionEstampa);
            estampaObj.setVisibilidad(visibilidadEstampa);
            estampaObj.setStock(stockEstampa);
            estampaObj.setCalificacion(calificacionEstampa);
            estampaObj.setPrecio(precioEstampa);
            estampaObj.setIdTema(temaObj);
            estampaObj.setIdArtista(artistaObj);

            Imagen imagenObj = new Imagen();
            imagenObj.setId(((int)imagenRepository.count())+1);
            imagenObj.setEnlace(enlaceImagen);
            imagenObj.setIdEstampa(estampaObj);

            temaRepository.save(temaObj);
            estampaRepository.save(estampaObj);
            imagenRepository.save(imagenObj);

            return true;
        } catch (Exception e) {
            return false;
        }


    }
}



