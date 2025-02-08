package com.notFound.demo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.notFound.demo.controllers.CatalogoController;
import com.notFound.demo.DTOs.TemaDTO;
import com.notFound.demo.entities.Catalogo;
import com.notFound.demo.entities.Tema;
import com.notFound.demo.repositories.CatalogoRepository;
import com.notFound.demo.repositories.TemaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
public class CatalogoControllerTest {

    @Mock
    private TemaRepository temaRepository;

    @Mock
    private CatalogoRepository catalogoRepository;

    @InjectMocks
    private CatalogoController catalogoController;

    // ------------------------ Pruebas para el endpoint "/" ------------------------
    @Test
    void CuandoExistenTemas() {
        // Configurar datos de prueba
        Tema temaMock = new Tema();
        temaMock.setId(1);
        temaMock.setNombre("Disney");

        when(temaRepository.findAll()).thenReturn(Arrays.asList(temaMock));

        // Ejecutar método
        ResponseEntity<List<TemaDTO>> respuesta = catalogoController.inicio();

        // Verificar resultados
        assertEquals(HttpStatus.OK, respuesta.getStatusCode());
        assertEquals(1, respuesta.getBody().size());
        assertEquals(1, respuesta.getBody().get(0).getId());
        assertEquals("Rock", respuesta.getBody().get(0).getNombre());
        verify(temaRepository, times(1)).findAll();
    }

    @Test
    void CuandoNoHayTemasEnBD() {
        when(temaRepository.findAll()).thenReturn(Collections.emptyList());

        ResponseEntity<List<TemaDTO>> respuesta = catalogoController.inicio();

        assertEquals(HttpStatus.OK, respuesta.getStatusCode());
        assertTrue(respuesta.getBody().isEmpty());
        verify(temaRepository).findAll();
    }

    // ------------------------ Pruebas para el endpoint "/catalogo" ------------------------
    @Test
    void catalogo_CuandoExistenRegistros() {
        // Configurar datos de prueba
        Catalogo catalogoMock = new Catalogo();
        catalogoMock.setId(100);
        catalogoMock.setNombreEstampa("Estampa Demo");
        catalogoMock.setPrecio(new BigDecimal("19900"));

        when(catalogoRepository.findAll()).thenReturn(Arrays.asList(catalogoMock));

        // Ejecutar método
        ResponseEntity<List<Catalogo>> respuesta = catalogoController.catalogo();

        // Verificar resultados
        assertEquals(HttpStatus.OK, respuesta.getStatusCode());
        assertEquals(1, respuesta.getBody().size());
        assertEquals(100, respuesta.getBody().get(0).getId());
        assertEquals("Estampa Demo", respuesta.getBody().get(0).getNombreEstampa());
        verify(catalogoRepository, times(1)).findAll();
    }

    @Test
    void catalogo_CuandoNoHayRegistros() {
        when(catalogoRepository.findAll()).thenReturn(Collections.emptyList());

        ResponseEntity<List<Catalogo>> respuesta = catalogoController.catalogo();

        assertEquals(HttpStatus.OK, respuesta.getStatusCode());
        assertTrue(respuesta.getBody().isEmpty());
        verify(catalogoRepository).findAll();
    }
}