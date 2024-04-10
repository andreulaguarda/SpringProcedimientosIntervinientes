package org.andreu.tarea2.msvcintervinientes.service;

import org.andreu.tarea2.msvcintervinientes.model.entity.Interviniente;
import java.util.List;
import java.util.Optional;

public interface IntervinienteService {

    List<Interviniente> findAll();

    Optional<Interviniente> findById(Long id);

    Interviniente save(Interviniente interviniente);

    Interviniente update(Interviniente interviniente);

    void deleteById(Long id);

}