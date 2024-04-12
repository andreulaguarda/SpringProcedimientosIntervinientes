package org.andreu.tarea2.msvcintervinientes.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.andreu.tarea2.msvcintervinientes.model.DatosAutoria;

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


    private String tipoIntervencion;

    @Embedded
    private DatosAutoria datosAutoria;

}