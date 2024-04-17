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

// Controlador REST para la entidad Procedimiento que permite realizar las operaciones CRUD
@RestController
@RequestMapping
public class ProcedimientoController {

    @Autowired
    private ProcedimientoService procedimientoService;

    // Devuelve una lista con todos los procedimientos mapeados a ProcedimientoDTO con un código de estado 200
    @GetMapping
    public ResponseEntity<List<ProcedimientoDTO>> getAllProcedimientos() {
        List<ProcedimientoDTO> procedimientos = procedimientoService.findAll();
        return new ResponseEntity<>(procedimientos, HttpStatus.OK);
    }

    // Devuelve un procedimiento mapeado a ProcedimientoDTO con un código 200 si existe, o un código 404 si no.
    @GetMapping("/{id}")
    public ResponseEntity<ProcedimientoDTO> getProcedimientoById(@PathVariable Long id) {

        Optional<ProcedimientoDTO> procedimientoDTO = procedimientoService.findById(id);

        if (procedimientoDTO.isPresent()) {
            return new ResponseEntity<>(procedimientoDTO.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Crea un nuevo procedimiento a partir de un ProcedimientoDTO y lo devuelve con un código 201
    @PostMapping
    public ResponseEntity<?> createProcedimiento(@Valid @RequestBody ProcedimientoDTO procedimientoDTO, BindingResult result) {
        if (result.hasErrors()) {
            return validar(result);
        }
        ProcedimientoDTO savedProcedimientoDTO = procedimientoService.save(procedimientoDTO);

        return new ResponseEntity<>(savedProcedimientoDTO, HttpStatus.CREATED);
    }

    // Actualiza un procedimiento a partir de un ProcedimientoDTO y lo devuelve con un código 200 si existe, o un 404 si no.
    @PutMapping("/{id}")
    public ResponseEntity<?> updateProcedimiento(@Valid @RequestBody ProcedimientoDTO procedimientoDTO, @PathVariable Long id, BindingResult result) {

        // Comprueba si hay errores de validación en el DTO y los devuelve en caso de que los haya
        if (result.hasErrors()) {
            return validar(result);
        }

        // Comprueba si existe el procedimiento con el id proporcionado
        Optional<ProcedimientoDTO> procedimiento = procedimientoService.findById(id);

        if (procedimiento.isPresent()) {
            procedimientoDTO.setId(id);
            ProcedimientoDTO updatedProcedimiento = procedimientoService.update(procedimientoDTO);
            return new ResponseEntity<>(updatedProcedimiento, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    // Elimina un procedimiento a partir de su id con un código 204 si existe, o un 404 si no.
    // También elimina los intervinientes relacionados con el procedimiento
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

    // Método auxiliar que devuelve un mensaje de error con los campos que no son válidos
    static ResponseEntity<Map<String, String>> validar(BindingResult result) {
        Map<String, String> errores = new HashMap<>();
        result.getFieldErrors().forEach(err -> errores.put(err.getField(), err.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errores);
    }

}
