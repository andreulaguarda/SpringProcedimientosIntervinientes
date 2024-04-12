package org.andreu.tarea2.msvcprocedimientos.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.andreu.tarea2.msvcprocedimientos.model.DatosAutoria;

import java.util.List;

@Data
public class ProcedimientoDTO {

    private Long id;

    // Valida que el numero de procedimiento no sea menor a 1 ni mayor a 999 ni nulo
    @Min(value = 1, message = "El numero de procedimiento no puede ser menor a 1")
    @Max(value = 9999, message = "El numero de procedimiento no puede ser mayor a 9999")
    @NotNull(message = "El numero de procedimiento no puede ser nulo")
    private Integer numProcedimiento;

    // Valida que el año no sea menor a 1900 ni mayor a 2100 ni nulo
    @Min(value = 1900, message = "El año no puede ser menor a 1900")
    @Max(value = 2100, message = "El año no puede ser mayor a 2100")
    @NotNull(message = "El año no puede ser nulo")
    private Integer anyo;

    private DatosAutoria datosAutoria;

    @NotEmpty(message = "La lista de intervinientes no puede estar vacia")
    private List<IntervinienteDTO> intervinientes;

}