package org.andreu.tarea2.msvcprocedimientos.model.entity;

import org.andreu.tarea2.msvcprocedimientos.dto.IntervinienteDTO;
import org.andreu.tarea2.msvcprocedimientos.dto.ProcedimientoDTO;
import org.andreu.tarea2.msvcprocedimientos.model.DatosAutoria;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProcedimientoTest {

    Procedimiento procedimiento1 = new Procedimiento();
    DatosAutoria datosAutoria = new DatosAutoria();

    @BeforeEach
    void setUp() {

        datosAutoria.setUsuarioCreacion("Test");
        datosAutoria.setFechaCreacion(LocalDate.now());
        datosAutoria.setUsuarioModificacion("Test");
        datosAutoria.setFechaModificacion(LocalDate.now());


        procedimiento1.setId(1L);
        procedimiento1.setNumProcedimiento(1);
        procedimiento1.setAnyo(2021);

        procedimiento1.setDatosAuditoria(datosAutoria);
    }

    @Test
    void getId() {
        assertEquals(1L, procedimiento1.getId());
    }

    @Test
    void getNumProcedimiento() {
            assertEquals(1, procedimiento1.getNumProcedimiento());
    }

    @Test
    void getAnyo() {
        assertEquals(2021, procedimiento1.getAnyo());
    }

    @Test
    void getDatosAuditoria() {
        assertEquals(datosAutoria, procedimiento1.getDatosAuditoria());
    }

    @Test
    void setId() {
        procedimiento1.setId(2L);
        assertEquals(2L, procedimiento1.getId());
    }

    @Test
    void setNumProcedimiento() {
        procedimiento1.setNumProcedimiento(2);
        assertEquals(2, procedimiento1.getNumProcedimiento());
    }

    @Test
    void setAnyo() {
        procedimiento1.setAnyo(2022);
        assertEquals(2022, procedimiento1.getAnyo());
    }

    @Test
    void setDatosAuditoria() {
        DatosAutoria datosAutoria2 = new DatosAutoria();
        datosAutoria2.setUsuarioCreacion("Test2");
        datosAutoria2.setFechaCreacion(LocalDate.now());
        datosAutoria2.setUsuarioModificacion("Test2");
        datosAutoria2.setFechaModificacion(LocalDate.now());
        procedimiento1.setDatosAuditoria(datosAutoria2);
        assertEquals(datosAutoria2, procedimiento1.getDatosAuditoria());
    }
}