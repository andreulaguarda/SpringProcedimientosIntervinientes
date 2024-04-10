package org.andreu.tarea2.msvcintervinientes.controller;

import org.andreu.tarea2.msvcintervinientes.model.entity.Interviniente;
import org.andreu.tarea2.msvcintervinientes.service.IntervinienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
public class IntervinienteController {

    @Autowired
    private IntervinienteService intervinienteService;

    @GetMapping
    public ResponseEntity<List<Interviniente>> findAll() {
        List<Interviniente> intervinientes = intervinienteService.findAll();
        return new ResponseEntity<>(intervinientes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Interviniente> findById(@PathVariable Long id) {
        return intervinienteService.findById(id)
                .map(interviniente -> new ResponseEntity<>(interviniente, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Interviniente> save(@RequestBody Interviniente interviniente) {
        Interviniente savedInterviniente = intervinienteService.save(interviniente);
        return new ResponseEntity<>(savedInterviniente, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Interviniente> update(@PathVariable Long id, @RequestBody Interviniente intervinienteDetails) {
        Optional<Interviniente> intervinienteOptional = intervinienteService.findById(id);
        if (intervinienteOptional.isPresent()) {
            intervinienteDetails.setId(id);
            Interviniente updatedInterviniente = intervinienteService.update(intervinienteDetails);
            return new ResponseEntity<>(updatedInterviniente, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        Optional<Interviniente> interviniente = intervinienteService.findById(id);
        if (interviniente.isPresent()) {
            intervinienteService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}