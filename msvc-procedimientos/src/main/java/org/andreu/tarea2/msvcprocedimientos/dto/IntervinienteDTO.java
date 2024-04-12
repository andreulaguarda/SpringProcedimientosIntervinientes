package org.andreu.tarea2.msvcprocedimientos.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.andreu.tarea2.msvcprocedimientos.model.DatosAutoria;
import org.andreu.tarea2.msvcprocedimientos.model.TipoIntervencion;

import java.util.Objects;


@Data
public class IntervinienteDTO {

    private Long id;

    // Valida que el nombre no sea nulo ni vacio
    @NotBlank(message = "El nombre no puede estar vacio")
    private String nombre;

    // Valida que la id del Procedimiento no sea nula ni vacia
    @NotBlank(message = "La id del procedimiento no puede estar vacia")
    private Long idProcedimiento;

    // Valida que el tipo de intervencion no sea nulo ni vacio
    @NotBlank(message = "El tipo de intervencion no puede estar vacio")
    private TipoIntervencion tipoIntervencion;

    private DatosAutoria datosAutoria;

    // Sobreescritura del metodo equals para comparar objetos de tipo IntervinienteDTO solo por su id
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IntervinienteDTO that = (IntervinienteDTO) o;
        return Objects.equals(id, that.id);
    }

}