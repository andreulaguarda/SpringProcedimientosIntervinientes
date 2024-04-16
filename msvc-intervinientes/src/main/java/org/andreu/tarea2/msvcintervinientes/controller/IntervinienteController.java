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

@RestController
@RequestMapping
public class IntervinienteController {

    @Autowired
    private IntervinienteService intervinienteService;

    @GetMapping
    public ResponseEntity<List<IntervinienteDTO>> findAll() {
        List<IntervinienteDTO> intervinientes = intervinienteService.findAll();
        return new ResponseEntity<>(intervinientes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<IntervinienteDTO> findById(@PathVariable Long id) {
        return intervinienteService.findById(id)
                .map(interviniente -> new ResponseEntity<>(interviniente, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/procedimiento/{idProcedimiento}")
    public ResponseEntity<List<IntervinienteDTO>> findbyIdProcedimiento(@PathVariable Long idProcedimiento) {
        List<IntervinienteDTO> intervinientes = intervinienteService.findbyIdProcedimiento(idProcedimiento);
        return new ResponseEntity<>(intervinientes, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody IntervinienteDTO intervinienteDTO, BindingResult result) {

        if (result.hasErrors()) {
            return validar(result);
        }

        IntervinienteDTO savedInterviniente = intervinienteService.save(intervinienteDTO);
        return new ResponseEntity<>(savedInterviniente, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody IntervinienteDTO intervinienteDetails, BindingResult result) {

        if (result.hasErrors()) {
            return validar(result);
        }

        Optional<IntervinienteDTO> intervinienteOptional = intervinienteService.findById(id);
        if (intervinienteOptional.isPresent()) {
            intervinienteDetails.setId(id);
            IntervinienteDTO updatedInterviniente = intervinienteService.update(intervinienteDetails);
            return new ResponseEntity<>(updatedInterviniente, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

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

    static ResponseEntity<Map<String, String>> validar(BindingResult result) {
        Map<String, String> errores = new HashMap<>();
        result.getFieldErrors().forEach(err -> errores.put(err.getField(), err.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errores);
    }
}