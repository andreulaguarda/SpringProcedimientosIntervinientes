package org.andreu.tarea2.msvcintervinientes.repository;

import org.andreu.tarea2.msvcintervinientes.model.entity.Interviniente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// Repositorio de Intervinientes
public interface IntervinienteRepository extends JpaRepository<Interviniente, Long> {

    // Busca Intervinientes por idProcedimiento y los devuelve en una lista
    List<Interviniente> findByIdProcedimiento(Long idProcedimiento);

}