package org.andreu.tarea2.msvcprocedimientos.service;

import org.andreu.tarea2.msvcprocedimientos.dto.ProcedimientoDTO;
import org.andreu.tarea2.msvcprocedimientos.model.entity.Procedimiento;

import java.util.List;

public interface ProcedimientoService {

    ProcedimientoDTO createProcedimiento(ProcedimientoDTO procedimientoDTO);

    ProcedimientoDTO getProcedimiento(Long id);

    ProcedimientoDTO updateProcedimiento(Long id, ProcedimientoDTO procedimientoDTO);

    void deleteProcedimiento(Long id);

    List<Procedimiento> getAllProcedimientos();

}