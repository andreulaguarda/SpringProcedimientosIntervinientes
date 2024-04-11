package org.andreu.tarea2.msvcprocedimientos.service;

import org.andreu.tarea2.msvcprocedimientos.dto.ProcedimientoDTO;
import org.andreu.tarea2.msvcprocedimientos.model.entity.Procedimiento;

import java.util.List;
import java.util.Optional;

public interface ProcedimientoService {

    List<ProcedimientoDTO> findAll();

    Optional<ProcedimientoDTO> findById(Long id);

    ProcedimientoDTO save(ProcedimientoDTO procedimientoDTO);

    ProcedimientoDTO update(ProcedimientoDTO procedimientoDTO);

    void deleteById(Long id);

}