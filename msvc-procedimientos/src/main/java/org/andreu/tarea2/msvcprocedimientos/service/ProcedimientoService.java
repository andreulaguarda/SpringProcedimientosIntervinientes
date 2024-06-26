package org.andreu.tarea2.msvcprocedimientos.service;

import org.andreu.tarea2.msvcprocedimientos.dto.ProcedimientoDTO;

import java.util.List;
import java.util.Optional;

// Interfaz de los servicios de Procedimiento que se implementa en ProcedimientoServiceImpl
public interface ProcedimientoService {

    List<ProcedimientoDTO> findAll();

    Optional<ProcedimientoDTO> findById(Long id);

    ProcedimientoDTO save(ProcedimientoDTO procedimientoDTO);
    ProcedimientoDTO update(ProcedimientoDTO procedimientoDTO);

    void deleteById(Long id);

    void deleteRelatedIntervinientes(Long id);
}