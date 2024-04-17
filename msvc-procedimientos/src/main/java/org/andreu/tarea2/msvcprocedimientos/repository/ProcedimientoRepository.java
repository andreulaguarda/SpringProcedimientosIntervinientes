package org.andreu.tarea2.msvcprocedimientos.repository;

import org.andreu.tarea2.msvcprocedimientos.model.entity.Procedimiento;
import org.springframework.data.jpa.repository.JpaRepository;

// Repositorio de Procedimientos que extiende de JpaRepository para realizar operaciones CRUD
public interface ProcedimientoRepository extends JpaRepository<Procedimiento, Long> {
}
