package org.andreu.tarea2.msvcintervinientes.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class DatosAutoriaTest {

    private DatosAutoria datosAutoria = new DatosAutoria();

    @BeforeEach
    void setUp() {
        datosAutoria.setUsuarioCreacion("Test");
        datosAutoria.setFechaCreacion(LocalDate.now());
        datosAutoria.setUsuarioModificacion("Test");
        datosAutoria.setFechaModificacion(LocalDate.now());
    }

    @Test
    void getFechaCreacion() {
        assertEquals(LocalDate.now(), datosAutoria.getFechaCreacion());
    }

    @Test
    void getUsuarioCreacion() {
        assertEquals("Test", datosAutoria.getUsuarioCreacion());
    }

    @Test
    void getFechaModificacion() {
        assertEquals(LocalDate.now(), datosAutoria.getFechaModificacion());
    }

    @Test
    void getUsuarioModificacion() {
        assertEquals("Test", datosAutoria.getUsuarioModificacion());
    }

    @Test
    void setUsuarioCreacion() {
        datosAutoria.setUsuarioCreacion("Test2");
        assertEquals("Test2", datosAutoria.getUsuarioCreacion());
    }

    @Test
    void setFechaCreacion() {
        LocalDate fecha = LocalDate.of(2021, 1, 1);
        datosAutoria.setFechaCreacion(fecha);
        assertEquals(fecha, datosAutoria.getFechaCreacion());
    }

    @Test
    void setUsuarioModificacion() {
        datosAutoria.setUsuarioModificacion("Test2");
        assertEquals("Test2", datosAutoria.getUsuarioModificacion());
    }

    @Test
    void setFechaModificacion() {
        LocalDate fecha = LocalDate.of(2021, 1, 1);
        datosAutoria.setFechaModificacion(fecha);
        assertEquals(fecha, datosAutoria.getFechaModificacion());
    }


}