package org.andreu.tarea2.msvcintervinientes.repository;

import org.andreu.tarea2.msvcintervinientes.model.entity.Interviniente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IntervinienteRepository extends JpaRepository<Interviniente, Long> {

    List<Interviniente> findByIdProcedimiento(Long idProcedimiento);

}