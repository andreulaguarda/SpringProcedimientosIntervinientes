package org.andreu.tarea2.msvcprocedimientos.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.andreu.tarea2.msvcprocedimientos.model.DatosAutoria;

import java.util.List;

@Entity
@Table(name = "procedimientos")
@Data
public class Procedimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "num_procedimiento")
    private Integer numProcedimiento;

    private Integer anyo;

    @Embedded
    private DatosAutoria datosAuditoria;

    @OneToMany(mappedBy = "procedimiento")
    private List<IdInterviniente> idsIntervinientes;

}