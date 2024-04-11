package org.andreu.tarea2.msvcprocedimientos.controller;

import org.andreu.tarea2.msvcprocedimientos.dto.ProcedimientoDTO;
import org.andreu.tarea2.msvcprocedimientos.service.ProcedimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
public class ProcedimientoController {

    @Autowired
    private ProcedimientoService procedimientoService;

    @PostMapping
    public ResponseEntity<ProcedimientoDTO> createProcedimiento(@RequestBody ProcedimientoDTO procedimientoDTO) {

        ProcedimientoDTO savedProcedimientoDTO = procedimientoService.save(procedimientoDTO);

        return new ResponseEntity<>(savedProcedimientoDTO, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ProcedimientoDTO>> getAllProcedimientos() {
        List<ProcedimientoDTO> procedimientos = procedimientoService.findAll();
        return new ResponseEntity<>(procedimientos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProcedimientoDTO> getProcedimientoById(@PathVariable Long id) {

        Optional<ProcedimientoDTO> procedimientoDTO = procedimientoService.findById(id);

        if (procedimientoDTO.isPresent()) {
            return new ResponseEntity<>(procedimientoDTO.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProcedimientoDTO> updateProcedimiento(@RequestBody ProcedimientoDTO procedimientoDTO, @PathVariable Long id) {

        Optional<ProcedimientoDTO> procedimiento = procedimientoService.findById(id);

        if (procedimiento.isPresent()) {
            procedimientoDTO.setId(id);
            procedimientoService.deleteRelatedIntervinientes(id);
            ProcedimientoDTO updatedProcedimiento = procedimientoService.save(procedimientoDTO);
            return new ResponseEntity<>(updatedProcedimiento, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProcedimiento(@PathVariable Long id) {

        Optional<ProcedimientoDTO> procedimiento = procedimientoService.findById(id);

        if (procedimiento.isPresent()) {
            procedimientoService.deleteRelatedIntervinientes(id);
            procedimientoService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

}
