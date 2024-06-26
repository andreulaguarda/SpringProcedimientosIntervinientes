package org.andreu.tarea2.msvcprocedimientos.dto;

import org.andreu.tarea2.msvcprocedimientos.model.DatosAutoria;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.validation.BindingResult;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class IntervinienteDTOTest {

    private IntervinienteDTO intervinienteDTO1 = new IntervinienteDTO();

    private IntervinienteDTO intervinienteDTO2 = new IntervinienteDTO();

    private DatosAutoria datosAutoria = new DatosAutoria();

    @BeforeEach
    void setUp() {

        datosAutoria.setUsuarioCreacion("Test");
        datosAutoria.setFechaCreacion(LocalDate.now());
        datosAutoria.setUsuarioModificacion("Test");
        datosAutoria.setFechaModificacion(LocalDate.now());

        intervinienteDTO1.setId(1L);
        intervinienteDTO1.setNombre("Interviniente 1");
        intervinienteDTO1.setTipoIntervencion("Tipo 1");
        intervinienteDTO1.setIdProcedimiento(1L);
        intervinienteDTO1.setDatosAutoria(datosAutoria);

    }

    @Test
    void testEquals() {
        IntervinienteDTO intervinienteCopia = intervinienteDTO1;
        assertEquals(intervinienteDTO1, intervinienteCopia);
        assertNotEquals(intervinienteDTO2, intervinienteCopia);
    }


    @Test
    void getIdProcedimiento() {
        assertEquals(1L, intervinienteDTO1.getIdProcedimiento());
    }

    @Test
    void getTipoIntervencion() {
        assertEquals("Tipo 1", intervinienteDTO1.getTipoIntervencion());
    }

    @Test
    void getDatosAutoria() {

        assertEquals(datosAutoria, intervinienteDTO1.getDatosAutoria());
    }

    @Test
    void setId() {
        intervinienteDTO2.setId(1L);
        assertEquals(1L, intervinienteDTO2.getId());
    }

    @Test
    void setNombre() {
        intervinienteDTO2.setNombre("Interviniente 2");
        assertEquals("Interviniente 2", intervinienteDTO2.getNombre());
    }

    @Test
    void setIdProcedimiento() {
            intervinienteDTO2.setIdProcedimiento(1L);
            assertEquals(1L, intervinienteDTO2.getIdProcedimiento());
    }

    @Test
    void setTipoIntervencion() {
        intervinienteDTO2.setTipoIntervencion("Tipo 2");
        assertEquals("Tipo 2", intervinienteDTO2.getTipoIntervencion());
    }

    @Test
    void setDatosAutoria() {
        intervinienteDTO2.setDatosAutoria(datosAutoria);
        assertEquals(datosAutoria, intervinienteDTO2.getDatosAutoria());
    }
}