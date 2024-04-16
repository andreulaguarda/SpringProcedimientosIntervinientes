package org.andreu.tarea2.msvcintervinientes.model.entity;

import org.andreu.tarea2.msvcintervinientes.model.DatosAutoria;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class IntervinienteTest {

    Interviniente interviniente = new Interviniente();

    DatosAutoria datosAutoria = new DatosAutoria();

    @BeforeEach
    void setUp() {

        datosAutoria.setUsuarioCreacion("Creacion Test");
        datosAutoria.setFechaCreacion(LocalDate.now());
        datosAutoria.setUsuarioModificacion("Modificacion Test");
        datosAutoria.setFechaModificacion(LocalDate.now());

        interviniente.setId(1L);
        interviniente.setNombre("Nombre Test");
        interviniente.setIdProcedimiento(1L);
        interviniente.setTipoIntervencion("Tipo Test");
        interviniente.setDatosAutoria(datosAutoria);
    }

    @Test
    void getId() {

        assertEquals(1L, interviniente.getId());
    }

    @Test
    void getNombre() {

        assertEquals("Nombre Test", interviniente.getNombre());
    }

    @Test
    void getIdProcedimiento() {

        assertEquals(1L, interviniente.getIdProcedimiento());
    }

    @Test
    void getTipoIntervencion() {

        assertEquals("Tipo Test", interviniente.getTipoIntervencion());
    }

    @Test
    void getDatosAutoria() {

        assertEquals(datosAutoria, interviniente.getDatosAutoria());
    }

    @Test
    void setId() {

        interviniente.setId(2L);
        assertEquals(2L, interviniente.getId());
    }

    @Test
    void setNombre() {

        interviniente.setNombre("Nombre Test 2");
        assertEquals("Nombre Test 2", interviniente.getNombre());
    }

    @Test
    void setIdProcedimiento() {

        interviniente.setIdProcedimiento(2L);
        assertEquals(2L, interviniente.getIdProcedimiento());
    }

    @Test
    void setTipoIntervencion() {

        interviniente.setTipoIntervencion("Tipo Test 2");
        assertEquals("Tipo Test 2", interviniente.getTipoIntervencion());
    }

    @Test
    void setDatosAutoria() {

        DatosAutoria datosAutoria2 = new DatosAutoria();
        datosAutoria2.setUsuarioCreacion("Creacion Test 2");
        datosAutoria2.setFechaCreacion(LocalDate.now());
        datosAutoria2.setUsuarioModificacion("Modificacion Test 2");
        datosAutoria2.setFechaModificacion(LocalDate.now());
        interviniente.setDatosAutoria(datosAutoria2);
        assertEquals(datosAutoria2, interviniente.getDatosAutoria());
    }
}