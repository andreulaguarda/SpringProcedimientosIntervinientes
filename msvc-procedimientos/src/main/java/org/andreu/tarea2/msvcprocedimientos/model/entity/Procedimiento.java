package org.andreu.tarea2.msvcprocedimientos.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.andreu.tarea2.msvcprocedimientos.model.DatosAutoria;

@Entity
@Table(name = "procedimientos")
@Data
public class Procedimiento {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer numProcedimiento;

    private Integer anyo;

    @Embedded
    private DatosAutoria datosAuditoria;

}