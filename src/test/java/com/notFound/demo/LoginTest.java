package com.notFound.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Optional;

import com.notFound.demo.controllers.ArtistaController;
import com.notFound.demo.controllers.ClienteController;
import com.notFound.demo.entities.Cliente;
import com.notFound.demo.entities.Artista;
import com.notFound.demo.repositories.ArtistaRepository;
import com.notFound.demo.repositories.ClienteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
public class LoginTest {

    @Mock
    private ClienteRepository clienteRepository; // Mock del repositorio

    @InjectMocks
    private ClienteController clienteController; // Controlador bajo prueba
// prueba de cliente existente con datos validos

    @Mock
    private ArtistaRepository artistaRepository; // Mock del repositorio

    @InjectMocks
    private ArtistaController artistaController; // Controlador bajo prueba
// prueba de artista existente con datos validos

    @Test
    void loginExitoso_RetornaIdClienteY200() {
        // Configurar datos de prueba
        Cliente clienteMock = new Cliente();
        clienteMock.setUsuario("pepe01");
        clienteMock.setContrasena("pepepassword");
        clienteMock.setId(1);

        // Simular comportamiento del repositorio
        when(clienteRepository.findByUsuario("pepe01"))
                .thenReturn(Optional.of(clienteMock));

        // Ejecutar el método
        ResponseEntity<Integer> respuesta = clienteController.login("pepe01", "pepepassword");

        // Verificar resultados
        assertEquals(HttpStatus.OK, respuesta.getStatusCode());
        assertEquals(1, respuesta.getBody());
        verify(clienteRepository, times(1)).findByUsuario("pepe01"); // Asegurar que se llamó al repositorio
    }

    @Test
    void loginExitoso_RetornaIdArtistaY200() {
        // Configurar datos de prueba
        Artista artistaMock = new Artista();
        artistaMock.setUsuario("pepe01");
        artistaMock.setContrasena("pepepassword");
        artistaMock.setId(1);

        // Simular comportamiento del repositorio
        when(artistaRepository.findByUsuario("pepe01"))
                .thenReturn(Optional.of(artistaMock));

        // Ejecutar el método
        ResponseEntity<Integer> respuesta = artistaController.login("pepe01", "pepepassword");

        // Verificar resultados
        assertEquals(HttpStatus.OK, respuesta.getStatusCode());
        assertEquals(1, respuesta.getBody());
        verify(artistaRepository, times(1)).findByUsuario("pepe01"); // Asegurar que se llamó al repositorio
    }
//prueba de contrasena incorrecta
    @Test
    void loginContrasenaIncorrecta_Retorna401() {
        Artista artistaMock = new Artista();
        artistaMock.setUsuario("pepe01");
        artistaMock.setContrasena("pepepassword");

        when(artistaRepository.findByUsuario("pepe01"))
                .thenReturn(Optional.of(artistaMock));

        ResponseEntity<Integer> respuesta = artistaController.login("pepe01", "contrasenaErronea");

        assertEquals(HttpStatus.UNAUTHORIZED, respuesta.getStatusCode());
        verify(artistaRepository).findByUsuario("pepe01");
    }

    @Test
    void loginContrasenaIncorrectaArtista_Retorna401() {
        Cliente clienteMock = new Cliente();
        clienteMock.setUsuario("pepe01");
        clienteMock.setContrasena("pepepassword");

        when(clienteRepository.findByUsuario("pepe01"))
                .thenReturn(Optional.of(clienteMock));

        ResponseEntity<Integer> respuesta = clienteController.login("pepe01", "contrasenaErronea");

        assertEquals(HttpStatus.UNAUTHORIZED, respuesta.getStatusCode());
        verify(clienteRepository).findByUsuario("pepe01");
    }
    //prueba de cliente no existe
    @Test
    void loginUsuarioNoExiste_Retorna401() {
        when(clienteRepository.findByUsuario("pepe01"))
                .thenReturn(Optional.empty());

        ResponseEntity<Integer> respuesta = clienteController.login("pepe01", "pepepassword");

        assertEquals(HttpStatus.UNAUTHORIZED, respuesta.getStatusCode());
        verify(clienteRepository).findByUsuario("pepe01");
    }

    @Test
    void loginUsuarioNoExisteArtista_Retorna401() {
        when(artistaRepository.findByUsuario("pepe01"))
                .thenReturn(Optional.empty());

        ResponseEntity<Integer> respuesta = artistaController.login("pepe01", "pepepassword");

        assertEquals(HttpStatus.UNAUTHORIZED, respuesta.getStatusCode());
        verify(artistaRepository).findByUsuario("pepe01");
    }

}


