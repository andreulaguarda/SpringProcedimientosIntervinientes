package org.andreu.tarea2.msvcintervinientes.model;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.time.LocalDate;

// Clase que representa los datos de autoría de un Interviniente
@Data
@Embeddable
public class DatosAutoria {
    private LocalDate fechaCreacion;
    private String usuarioCreacion;
    private LocalDate fechaModificacion;
    private String usuarioModificacion;
}