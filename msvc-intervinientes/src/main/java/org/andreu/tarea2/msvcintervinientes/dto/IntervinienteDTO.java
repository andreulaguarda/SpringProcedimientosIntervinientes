package org.andreu.tarea2.msvcintervinientes.dto;

import lombok.Data;
import org.andreu.tarea2.msvcintervinientes.model.DatosAutoria;
import org.andreu.tarea2.msvcintervinientes.model.TipoIntervencion;

@Data
public class IntervinienteDTO {

    private Long id;
    private String nombre;
    private Long idProcedimiento;
    private TipoIntervencion tipoIntervencion;
    private DatosAutoria datosAutoria;

}