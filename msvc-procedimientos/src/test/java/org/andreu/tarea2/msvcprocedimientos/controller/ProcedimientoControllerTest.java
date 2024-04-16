package org.andreu.tarea2.msvcprocedimientos.controller;

import org.andreu.tarea2.msvcprocedimientos.dto.IntervinienteDTO;
import org.andreu.tarea2.msvcprocedimientos.dto.ProcedimientoDTO;
import org.andreu.tarea2.msvcprocedimientos.service.ProcedimientoServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProcedimientoControllerTest {

    @InjectMocks
    private ProcedimientoController procedimientoController;

    @Mock
    private ProcedimientoServiceImpl procedimientoServiceImpl;

    ProcedimientoDTO procedimientoDTO1 = new ProcedimientoDTO();

    ProcedimientoDTO procedimientoDTO2 = new ProcedimientoDTO();

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

        procedimientoDTO1.setId(1L);
        procedimientoDTO1.setNumProcedimiento(1);
        procedimientoDTO1.setAnyo(2021);
        procedimientoDTO1.setIntervinientes(List.of(intervinienteDTO1, intervinienteDTO2));

        procedimientoDTO2.setId(2L);
        procedimientoDTO2.setNumProcedimiento(2);
        procedimientoDTO2.setAnyo(2022);
        procedimientoDTO2.setIntervinientes(List.of(intervinienteDTO1));
    }


    @Test
    void getAllProcedimientos() {
        ResponseEntity<List<ProcedimientoDTO>> listaProcedimientosTest = procedimientoController.getAllProcedimientos();

        verify(procedimientoServiceImpl, times(1)).findAll();

        assertEquals(new ResponseEntity<>(Collections.emptyList(), HttpStatus.OK), listaProcedimientosTest);
    }

    @Test
    void getProcedimientoById() {

        when(procedimientoServiceImpl.findById(1L)).thenReturn(Optional.of(procedimientoDTO1));

        ResponseEntity<ProcedimientoDTO> procedimientoDTOTest = procedimientoController.getProcedimientoById(1L);

        ResponseEntity<ProcedimientoDTO> procedimientoDTONotFound = procedimientoController.getProcedimientoById(10L);

        verify(procedimientoServiceImpl, times(1)).findById(1L);

        assertEquals(ResponseEntity.ok(procedimientoDTO1), procedimientoDTOTest);

        assertEquals(ResponseEntity.notFound().build(), procedimientoDTONotFound);

    }


    @Test
    void createProcedimiento() {

        ResponseEntity procedimientoDTOTest = procedimientoController.createProcedimiento(procedimientoDTO1, result);

        verify(procedimientoServiceImpl, times(1)).save(procedimientoDTO1);

        assertEquals(ResponseEntity.status(HttpStatus.CREATED).build(), procedimientoDTOTest);
    }

    @Test
    void updateProcedimiento() {

        when(procedimientoServiceImpl.findById(1L)).thenReturn(Optional.of(procedimientoDTO1));

        ResponseEntity ProcedimientoTest =  procedimientoController.updateProcedimiento(procedimientoDTO1, 1L, result);

        verify(procedimientoServiceImpl, times(1)).update(procedimientoDTO1);

        assertEquals(ResponseEntity.ok().build(), ProcedimientoTest);
    }

    @Test
    void deleteProcedimiento() {

        when(procedimientoServiceImpl.findById(1L)).thenReturn(Optional.of(procedimientoDTO1));

        ResponseEntity ProcedimientoTest =  procedimientoController.deleteProcedimiento(1L);

        verify(procedimientoServiceImpl, times(1)).deleteById(1L);

        assertEquals(ResponseEntity.noContent().build(), ProcedimientoTest);

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
    ResponseEntity<Map<String, String>> responseEntity = procedimientoController.validar(result);

    // Verificar que la respuesta es correcta
    assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    assertTrue(responseEntity.getBody().containsKey("field"));
    assertEquals("defaultMessage", responseEntity.getBody().get("field"));
}
}