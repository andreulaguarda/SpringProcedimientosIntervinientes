package org.andreu.tarea2.msvcintervinientes.service;

import org.andreu.tarea2.msvcintervinientes.dto.IntervinienteDTO;
import org.andreu.tarea2.msvcintervinientes.model.entity.Interviniente;
import java.util.List;
import java.util.Optional;

public interface IntervinienteService {

    List<IntervinienteDTO> findAll();

    Optional<IntervinienteDTO> findById(Long id);

    IntervinienteDTO save(IntervinienteDTO intervinienteDTO);

    IntervinienteDTO update(IntervinienteDTO intervinienteDTO);

    void deleteById(Long id);

}