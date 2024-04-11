package org.andreu.tarea2.msvcprocedimientos.dto;

import lombok.Data;
import org.andreu.tarea2.msvcprocedimientos.model.DatosAutoria;
import org.andreu.tarea2.msvcprocedimientos.model.Interviniente;

import java.util.List;

@Data
public class ProcedimientoDTO {

    private Long id;
    private Integer numProcedimiento;
    private Integer anyo;
    private DatosAutoria datosAutoria;
    private List<Interviniente> Intervinientes;

}