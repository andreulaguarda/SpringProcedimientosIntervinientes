package org.andreu.tarea2.msvcprocedimientos.model;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.time.LocalDate;

// Clase que representa los datos de autoría de un Procedimiento o Interviniente
@Data
@Embeddable
public class DatosAutoria {

    private LocalDate fechaCreacion;

    private String usuarioCreacion;

    private LocalDate fechaModificacion;

    private String usuarioModificacion;
}