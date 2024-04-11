package org.andreu.tarea2.msvcprocedimientos.controller;

import org.andreu.tarea2.msvcprocedimientos.client.IntervinienteClientRest;
import org.andreu.tarea2.msvcprocedimientos.dto.ProcedimientoDTO;
import org.andreu.tarea2.msvcprocedimientos.model.Interviniente;
import org.andreu.tarea2.msvcprocedimientos.model.entity.Procedimiento;
import org.andreu.tarea2.msvcprocedimientos.service.ProcedimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class ProcedimientoController {

    @Autowired
    private ProcedimientoService procedimientoService;

    @Autowired
    private IntervinienteClientRest IntervinienteClientRest;

    @PostMapping
    public ResponseEntity<ProcedimientoDTO> createProcedimiento(@RequestBody ProcedimientoDTO procedimientoDTO) {

        ProcedimientoDTO savedProcedimientoDTO = procedimientoService.createProcedimiento(procedimientoDTO);

        return new ResponseEntity<>(savedProcedimientoDTO, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Procedimiento>> getAllProcedimientos() {
        List<Procedimiento> procedimientos = procedimientoService.getAllProcedimientos();
        return new ResponseEntity<>(procedimientos, HttpStatus.OK);
    }


    @GetMapping("/testfeign/{id}")
    public Interviniente getInterviniente(@PathVariable Long id) {
        return IntervinienteClientRest.getInterviniente(id);
    }
}
