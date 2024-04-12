package org.andreu.tarea2.msvcintervinientes.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.andreu.tarea2.msvcintervinientes.model.DatosAutoria;
import org.andreu.tarea2.msvcintervinientes.model.TipoIntervencion;

@Data
public class IntervinienteDTO {

    private Long id;

    // Valida que el nombre no sea nulo ni vacio
    @NotBlank(message = "El nombre no puede estar vacio")
    private String nombre;

    // Valida que la id del Procedimiento no sea nula ni vacia
    @NotNull(message = "La id del procedimiento no puede estar vacia")
    @Min(value = 1, message = "La id del procedimiento no puede ser menor que 1")
    private Long idProcedimiento;

    // Valida que el tipo de intervencion no sea nulo ni vacio
    @NotNull(message = "El tipo de intervencion no puede ser nulo")
    private TipoIntervencion tipoIntervencion;

    private DatosAutoria datosAutoria;

}