package org.andreu.tarea2.msvcprocedimientos.model;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;

import java.time.LocalDate;

@Data
@Embeddable
public class DatosAutoria {


    private LocalDate fechaCreacion;

    private String usuarioCreacion;

    private LocalDate fechaModificacion;

    private String usuarioModificacion;
}