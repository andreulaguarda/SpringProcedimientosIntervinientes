package org.andreu.tarea2.msvcprocedimientos.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "ids_intervinientes")
public class IdInterviniente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long intervinienteId;

    @ManyToOne
    @JoinColumn(name = "procedimiento_id")
    private Procedimiento procedimiento;
}
