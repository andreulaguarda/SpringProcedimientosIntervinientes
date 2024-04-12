package org.andreu.tarea2.msvcprocedimientos.controller;

import jakarta.validation.Valid;
import org.andreu.tarea2.msvcprocedimientos.dto.ProcedimientoDTO;
import org.andreu.tarea2.msvcprocedimientos.service.ProcedimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping
public class ProcedimientoController {

    @Autowired
    private ProcedimientoService procedimientoService;

    @PostMapping
    public ResponseEntity<?> createProcedimiento(@Valid @RequestBody ProcedimientoDTO procedimientoDTO, BindingResult result) {
        if (result.hasErrors()) {
            return validar(result);
        }
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
            ProcedimientoDTO updatedProcedimiento = procedimientoService.update(procedimientoDTO);
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

    private static ResponseEntity<Map<String, String>> validar(BindingResult result) {
        Map<String, String> errores = new HashMap<>();
        result.getFieldErrors().forEach(err -> errores.put(err.getField(), err.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errores);
    }

}
