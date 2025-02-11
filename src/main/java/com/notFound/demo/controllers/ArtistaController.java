package com.notFound.demo.controllers;

import com.notFound.demo.entities.*;
import com.notFound.demo.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;



@RestController
@RequestMapping("/artista")
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

    @Autowired
    DireccionRepository direccionRepository;


    private Artista artistaObj;

    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("/login")
    public ResponseEntity<Integer> login(@RequestParam String usuario, @RequestParam String contrasena) {

        Optional<Artista> artista = artistaRepository.findByUsuario(usuario);

        if (artista.isPresent() && artista.get().getContrasena().equals(contrasena)) {

            artistaObj = artista.get();
            System.out.println(artista.get().getId());
            return ResponseEntity.ok(artista.get().getId()); // Retorna ID con status 200

        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); // Credenciales inválidas

    }

    @GetMapping("/register")
    public boolean register(@RequestParam String nombre,
                             @RequestParam String apellido,
                             @RequestParam String usuario,
                             @RequestParam String contrasena,
                             @RequestParam String tipo_id,
                             @RequestParam String correo,
                             @RequestParam String numero_id){


        try {
            Artista artistaObj = new Artista();
            artistaObj.setId(((int)artistaRepository.count())+10001);
            artistaObj.setNombre(nombre);
            artistaObj.setApellido(apellido);
            artistaObj.setUsuario(usuario);
            artistaObj.setContrasena(contrasena);
            artistaObj.setTipoId(tipo_id);
            artistaObj.setCorreo(correo);
            BigDecimal numeroId = new BigDecimal(numero_id);
            artistaObj.setNumeroId(numeroId);


            artistaRepository.save(artistaObj);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    @Transactional
    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("/registerMedio")
    public boolean registerMedio(

            @RequestParam String numero_tarjeta,
            @RequestParam String tipo_tarjeta,
            @RequestParam LocalDate f_vencimiento
    ){
        try {
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

    @CrossOrigin(origins = "http://localhost:5173")
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
            Optional<Tema> temaObj1 = temaRepository.findByNombre( nombreTema);
            if (temaObj1.isPresent()) {
                temaObj.setNombre(temaObj1.get().getNombre());
                temaObj.setId(temaObj1.get().getId());
            }
            else{

                temaObj.setId(((int)temaRepository.count())+1);
                temaObj.setNombre(nombreTema);
            }


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



