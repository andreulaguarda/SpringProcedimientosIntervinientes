package org.andreu.tarea2.msvcintervinientes.controller;

import jakarta.validation.Valid;
import org.andreu.tarea2.msvcintervinientes.dto.IntervinienteDTO;
import org.andreu.tarea2.msvcintervinientes.service.IntervinienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

// Controlador REST para la entidad Interviniente que permite realizar las operaciones CRUD
@RestController
@RequestMapping
public class IntervinienteController {

    @Autowired
    private IntervinienteService intervinienteService;

    // Devuelve una lista de todos los intervinientes
    @GetMapping
    public ResponseEntity<List<IntervinienteDTO>> findAll() {
        List<IntervinienteDTO> intervinientes = intervinienteService.findAll();

        // Devuelve la lista de intervinientes y el código de estado 200 (OK)
        return new ResponseEntity<>(intervinientes, HttpStatus.OK);
    }

    // Devuelve un interviniente por su id o un código de estado 404 (NOT_FOUND) si no se encuentra
    @GetMapping("/{id}")
    public ResponseEntity<IntervinienteDTO> findById(@PathVariable Long id) {
        return intervinienteService.findById(id)
                .map(interviniente -> new ResponseEntity<>(interviniente, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Devuelve una lista de intervinientes por el id de un procedimiento
    // o un código de estado 404 (NOT_FOUND) si no se encuentra
    @GetMapping("/procedimiento/{idProcedimiento}")
    public ResponseEntity<List<IntervinienteDTO>> findbyIdProcedimiento(@PathVariable Long idProcedimiento) {
        List<IntervinienteDTO> intervinientes = intervinienteService.findbyIdProcedimiento(idProcedimiento);
        return new ResponseEntity<>(intervinientes, HttpStatus.OK);
    }

    // Guarda un interviniente en la base de datos
    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody IntervinienteDTO intervinienteDTO, BindingResult result) {

        // Si los datos de entrada no son válidos, devuelve un mensaje de error
        if (result.hasErrors()) {
            return validar(result);
        }

        IntervinienteDTO savedInterviniente = intervinienteService.save(intervinienteDTO);

        // Devuelve el interviniente guardado y el código de estado 201 (CREATED)
        return new ResponseEntity<>(savedInterviniente, HttpStatus.CREATED);
    }

    // Actualiza un interviniente en la base de datos
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody IntervinienteDTO intervinienteDetails, BindingResult result) {

        // Si los datos de entrada no son válidos, devuelve un mensaje de error
        if (result.hasErrors()) {
            return validar(result);
        }

        // Busca el interviniente por su id y lo actualiza
        Optional<IntervinienteDTO> intervinienteOptional = intervinienteService.findById(id);
        if (intervinienteOptional.isPresent()) {
            intervinienteDetails.setId(id);
            IntervinienteDTO updatedInterviniente = intervinienteService.update(intervinienteDetails);

            // Devuelve el interviniente actualizado y el código de estado 200 (OK)
            return new ResponseEntity<>(updatedInterviniente, HttpStatus.OK);
        } else {

            // Devuelve un código de estado 404 (NOT_FOUND) si no se encuentra el interviniente
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Elimina un interviniente por su id o un código de estado 404 (NOT_FOUND) si no se encuentra
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        Optional<IntervinienteDTO> interviniente = intervinienteService.findById(id);
        if (interviniente.isPresent()) {
            intervinienteService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Devuelve un mensaje de error con los campos que no son válidos
    static ResponseEntity<Map<String, String>> validar(BindingResult result) {
        Map<String, String> errores = new HashMap<>();
        result.getFieldErrors().forEach(err -> errores.put(err.getField(), err.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errores);
    }
}