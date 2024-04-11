package org.andreu.tarea2.msvcprocedimientos.model;

import jakarta.persistence.*;
import lombok.Data;


@Data
public class Interviniente {


    private Long id;

    private String nombre;

    @Enumerated(EnumType.STRING)
    private TipoIntervencion tipoIntervencion;

    @Embedded
    private DatosAutoria datosAutoria;

}