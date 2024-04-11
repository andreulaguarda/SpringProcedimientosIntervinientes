package org.andreu.tarea2.msvcintervinientes.service;

import org.andreu.tarea2.msvcintervinientes.dto.IntervinienteDTO;

import java.util.List;
import java.util.Optional;

public interface IntervinienteService {

    List<IntervinienteDTO> findAll();

    List<IntervinienteDTO> findbyIdProcedimiento(Long idProcedimiento);

    Optional<IntervinienteDTO> findById(Long id);

    IntervinienteDTO save(IntervinienteDTO intervinienteDTO);

    void deleteById(Long id);



}