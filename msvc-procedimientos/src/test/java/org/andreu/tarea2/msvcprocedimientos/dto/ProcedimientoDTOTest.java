package org.andreu.tarea2.msvcprocedimientos.dto;

import org.andreu.tarea2.msvcprocedimientos.dto.IntervinienteDTO;
import org.andreu.tarea2.msvcprocedimientos.dto.ProcedimientoDTO;
import org.andreu.tarea2.msvcprocedimientos.model.DatosAutoria;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProcedimientoDTOTest {

    private ProcedimientoDTO procedimientoDTO1 = new ProcedimientoDTO();

    private ProcedimientoDTO procedimientoDTO2 = new ProcedimientoDTO();

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

        intervinienteDTO2.setId(2L);
        intervinienteDTO2.setNombre("Interviniente 2");
        intervinienteDTO2.setTipoIntervencion("Tipo 2");

        procedimientoDTO1.setId(1L);
        procedimientoDTO1.setNumProcedimiento(1);
        procedimientoDTO1.setAnyo(2021);
        procedimientoDTO1.setIntervinientes(List.of(intervinienteDTO1, intervinienteDTO2));
        procedimientoDTO1.setDatosAutoria(datosAutoria);

        procedimientoDTO2.setId(2L);
        procedimientoDTO2.setNumProcedimiento(2);
        procedimientoDTO2.setAnyo(2022);
        procedimientoDTO2.setIntervinientes(List.of(intervinienteDTO1));
    }


    @Test
    void getId() {
        assertEquals(1L, procedimientoDTO1.getId());
    }

    @Test
    void getNumProcedimiento() {

        assertEquals(1, procedimientoDTO1.getNumProcedimiento());
    }

    @Test
    void getAnyo() {

        assertEquals(2021, procedimientoDTO1.getAnyo());
    }

    @Test
    void getDatosAutoria() {

        assertEquals(datosAutoria, procedimientoDTO1.getDatosAutoria());
    }

    @Test
    void getIntervinientes() {

            assertEquals(List.of(intervinienteDTO1, intervinienteDTO2), procedimientoDTO1.getIntervinientes());
    }

    @Test
    void setId() {
        procedimientoDTO2.setId(2L);
        assertEquals(2L, procedimientoDTO2.getId());
    }

    @Test
    void setNumProcedimiento() {
        procedimientoDTO2.setNumProcedimiento(2);
        assertEquals(2, procedimientoDTO2.getNumProcedimiento());
    }

    @Test
    void setAnyo() {
        procedimientoDTO2.setAnyo(2022);
        assertEquals(2022, procedimientoDTO2.getAnyo());
    }

    @Test
    void setDatosAutoria() {
        procedimientoDTO2.setDatosAutoria(datosAutoria);
        assertEquals(datosAutoria, procedimientoDTO2.getDatosAutoria());
    }

    @Test
    void setIntervinientes() {
        procedimientoDTO2.setIntervinientes(List.of(intervinienteDTO1));
        assertEquals(List.of(intervinienteDTO1), procedimientoDTO2.getIntervinientes());
    }
}