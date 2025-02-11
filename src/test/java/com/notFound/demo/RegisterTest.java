package com.notFound.demo;

import com.notFound.demo.controllers.ArtistaController;
import com.notFound.demo.controllers.ClienteController;
import com.notFound.demo.entities.Artista;
import com.notFound.demo.entities.Cliente;

import com.notFound.demo.entities.MedioDePago;
import com.notFound.demo.repositories.ArtistaRepository;
import com.notFound.demo.repositories.ClienteRepository;
import com.notFound.demo.repositories.MedioDePagoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class RegisterTest {

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private MedioDePagoRepository medioDePagoRepository;

    @InjectMocks
    private ClienteController clienteController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

    }

    @Mock
    private ArtistaRepository artistaRepository;

    @InjectMocks
    private ArtistaController artistaController;


    @Test
    public void testRegister_Success() {
        // Arrange
        String nombre = "Juan";
        String apellido = "Perez";
        String usuario = "juanperez";
        String contrasena = "password123";
        String tipo_id = "CC";
        String correo = "juan@example.com";
        BigDecimal numero_id = new BigDecimal("123456789");
        String numero_tarjeta = "1234-5678-9012-3456";
        String tipo_tarjeta = "Visa";
        LocalDate f_vencimiento = LocalDate.of(2025, 12, 31);

        // Simular el comportamiento de los repositorios
        when(clienteRepository.count()).thenReturn(0L); // Simula que no hay clientes registrados
        when(medioDePagoRepository.count()).thenReturn(0L); // Simula que no hay medios de pago registrados
        when(clienteRepository.save(any(Cliente.class))).thenAnswer(invocation -> invocation.getArgument(0));
        when(medioDePagoRepository.save(any(MedioDePago.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
      //  boolean result = clienteController.register(nombre, apellido, usuario, contrasena, tipo_id, correo, numero_id);

        // Assert
       // assertTrue(result); // Verifica que el registro fue exitoso
        verify(clienteRepository, times(1)).save(any(Cliente.class)); // Verifica que se llamó a save en ClienteRepository
        verify(medioDePagoRepository, times(1)).save(any(MedioDePago.class)); // Verifica que se llamó a save en MedioDePagoRepository
    }

    @Test
    public void testRegister_Failure() {
        // Arrange
        String nombre = "Juan";
        String apellido = "Perez";
        String usuario = "juanperez";
        String contrasena = "password123";
        String tipo_id = "CC";
        String correo = "juan@example.com";
        BigDecimal numero_id = new BigDecimal("123456789");
        String numero_tarjeta = "1234-5678-9012-3456";
        String tipo_tarjeta = "Visa";
        LocalDate f_vencimiento = LocalDate.of(2025, 12, 31);

        // Simular una excepción al guardar en el repositorio
        when(clienteRepository.count()).thenReturn(0L);
        when(medioDePagoRepository.count()).thenReturn(0L);
        when(clienteRepository.save(any(Cliente.class))).thenThrow(new RuntimeException("Error al guardar"));

        // Act
        //boolean result = clienteController.register(nombre, apellido, usuario, contrasena, tipo_id, correo, numero_id);

        // Assert
      //  assertTrue(!result); // Verifica que el registro falló
        verify(clienteRepository, times(1)).save(any(Cliente.class)); // Verifica que se intentó guardar el cliente
        verify(medioDePagoRepository, times(0)).save(any(MedioDePago.class)); // Verifica que no se intentó guardar el medio de pago
    }

    @Test
    public void testRegisterArtista_Success() {
        // Arrange
        String nombre = "Juan";
        String apellido = "Perez";
        String usuario = "juanperez";
        String contrasena = "password123";
        String tipo_id = "CC";
        String correo = "juan@example.com";
        String numero_id = "123456789";
        String numero_tarjeta = "1234-5678-9012-3456";
        String tipo_tarjeta = "Visa";
        LocalDate f_vencimiento = LocalDate.of(2025, 12, 31);

        // Simular el comportamiento de los repositorios
        when(artistaRepository.count()).thenReturn(0L); // Simula que no hay artistas registrados
        when(medioDePagoRepository.count()).thenReturn(0L); // Simula que no hay medios de pago registrados
        when(artistaRepository.save(any(Artista.class))).thenAnswer(invocation -> invocation.getArgument(0));
        when(medioDePagoRepository.save(any(MedioDePago.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        boolean result = artistaController.register(nombre, apellido, usuario, contrasena, tipo_id, correo, numero_id);

        // Assert
        assertTrue(result); // Verifica que el registro fue exitoso
        verify(artistaRepository, times(1)).save(any(Artista.class)); // Verifica que se llamó a save en ArtistaRepository
        verify(medioDePagoRepository, times(1)).save(any(MedioDePago.class)); // Verifica que se llamó a save en MedioDePagoRepository
    }

    @Test
    public void testRegisterArtista_Failure() {
        // Arrange
        String nombre = "Juan";
        String apellido = "Perez";
        String usuario = "juanperez";
        String contrasena = "password123";
        String tipo_id = "CC";
        String correo = "juan@example.com";
        String numero_id = "123456789";
        String numero_tarjeta = "1234-5678-9012-3456";
        String tipo_tarjeta = "Visa";
        LocalDate f_vencimiento = LocalDate.of(2025, 12, 31);

        // Simular una excepción al guardar en el repositorio
        when(artistaRepository.count()).thenReturn(0L);
        when(medioDePagoRepository.count()).thenReturn(0L);
        when(artistaRepository.save(any(Artista.class))).thenThrow(new RuntimeException("Error al guardar"));

        // Act
        boolean result = artistaController.register(nombre, apellido, usuario, contrasena, tipo_id, correo, numero_id);

        // Assert
        assertTrue(!result); // Verifica que el registro falló
        verify(artistaRepository, times(1)).save(any(Artista.class)); // Verifica que se intentó guardar el artista
        verify(medioDePagoRepository, times(0)).save(any(MedioDePago.class)); // Verifica que no se intentó guardar el medio de pago
    }

}