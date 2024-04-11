package org.andreu.tarea2.msvcintervinientes.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.andreu.tarea2.msvcintervinientes.model.DatosAutoria;
import org.andreu.tarea2.msvcintervinientes.model.TipoIntervencion;

@Entity
@Table(name = "intervinientes")
@Data
public class Interviniente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @Column(name = "id_procedimiento")
    private Long idProcedimiento;

    @Enumerated(EnumType.STRING)
    private TipoIntervencion tipoIntervencion;

    @Embedded
    private DatosAutoria datosAutoria;

}