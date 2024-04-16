package org.andreu.tarea2.msvcintervinientes.controller;

import org.andreu.tarea2.msvcintervinientes.dto.IntervinienteDTO;
import org.andreu.tarea2.msvcintervinientes.service.IntervinienteService;
import org.andreu.tarea2.msvcintervinientes.service.IntervinienteServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class IntervinienteControllerTest {

    @InjectMocks
    private IntervinienteController intervinienteController;

    @Mock
    private IntervinienteService intervinienteService;

    IntervinienteDTO intervinienteDTO1 = new IntervinienteDTO();

    IntervinienteDTO intervinienteDTO2 = new IntervinienteDTO();

    BindingResult result;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.initMocks(this);

        result = mock(BindingResult.class);

        intervinienteDTO1.setId(1L);
        intervinienteDTO1.setNombre("Interviniente 1");
        intervinienteDTO1.setTipoIntervencion("Tipo 1");

        intervinienteDTO2.setId(2L);
        intervinienteDTO2.setNombre("Interviniente 2");
        intervinienteDTO2.setTipoIntervencion("Tipo 2");
    }

    @Test
    void findAll() {

        ResponseEntity<List<IntervinienteDTO>> listaIntervinientesTest = intervinienteController.findAll();

        verify(intervinienteService, times(1)).findAll();

        assertEquals(ResponseEntity.ok().build().getStatusCodeValue(), listaIntervinientesTest.getStatusCodeValue());

    }

    @Test
    void findById() {

        when(intervinienteService.findById(1L)).thenReturn(java.util.Optional.of(intervinienteDTO1));

        ResponseEntity<IntervinienteDTO> intervinienteDTOTest = intervinienteController.findById(1L);

        verify(intervinienteService, times(1)).findById(1L);

        assertEquals(ResponseEntity.ok().build().getStatusCodeValue(), intervinienteDTOTest.getStatusCodeValue());

    }

    @Test
    void findbyIdProcedimiento() {

        when(intervinienteService.findbyIdProcedimiento(1L)).thenReturn(List.of(intervinienteDTO1, intervinienteDTO2));

        ResponseEntity<List<IntervinienteDTO>> listaIntervinientesTest = intervinienteController.findbyIdProcedimiento(1L);

        verify(intervinienteService, times(1)).findbyIdProcedimiento(1L);

        assertEquals(ResponseEntity.ok().build().getStatusCodeValue(), listaIntervinientesTest.getStatusCodeValue());

    }

    @Test
    void save() {

        when(intervinienteService.save(intervinienteDTO1)).thenReturn(intervinienteDTO1);

        ResponseEntity intervinienteDTOTest = intervinienteController.save(intervinienteDTO1, result);

        verify(intervinienteService, times(1)).save(intervinienteDTO1);

        assertEquals(ResponseEntity.created(null).build().getStatusCodeValue(),
                intervinienteDTOTest.getStatusCodeValue());
    }

    @Test
    void update() {

        when(intervinienteService.findById(1L)).thenReturn(java.util.Optional.of(intervinienteDTO1));

        ResponseEntity intervinienteDTOTest = intervinienteController.update(1L, intervinienteDTO1, result);

        verify(intervinienteService, times(1)).update(intervinienteDTO1);

        assertEquals(ResponseEntity.ok().build(), intervinienteDTOTest);

    }

    @Test
    void deleteById() {

        when(intervinienteService.findById(1L)).thenReturn(java.util.Optional.of(intervinienteDTO1));

        ResponseEntity intervinienteDTOTest = intervinienteController.deleteById(1L);

        verify(intervinienteService, times(1)).deleteById(1L);

        assertEquals(ResponseEntity.noContent().build(), intervinienteDTOTest);

    }

    @Test
    void validar() {
        // Crear un BindingResult ficticio
        BindingResult result = mock(BindingResult.class);

        // Configurar el BindingResult para que tenga errores
        when(result.hasErrors()).thenReturn(true);

        // Crear un FieldError ficticio
        FieldError error = new FieldError("objectName", "field", "defaultMessage");

        // Configurar el BindingResult para que devuelva una lista de FieldError
        when(result.getFieldErrors()).thenReturn(Collections.singletonList(error));

        // Llamar al método validar
        ResponseEntity<Map<String, String>> responseEntity = intervinienteController.validar(result);

        // Verificar que la respuesta es correcta
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertTrue(responseEntity.getBody().containsKey("field"));
        assertEquals("defaultMessage", responseEntity.getBody().get("field"));
    }

}