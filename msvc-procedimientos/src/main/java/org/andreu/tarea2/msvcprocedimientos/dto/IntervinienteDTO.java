package org.andreu.tarea2.msvcprocedimientos.dto;

import lombok.Data;
import org.andreu.tarea2.msvcprocedimientos.model.DatosAutoria;
import org.andreu.tarea2.msvcprocedimientos.model.TipoIntervencion;


@Data
public class IntervinienteDTO {

    private Long id;
    private String nombre;
    private Long idProcedimiento;
    private TipoIntervencion tipoIntervencion;
    private DatosAutoria datosAutoria;

}