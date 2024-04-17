package org.andreu.tarea2.msvcprocedimientos.client;

import org.andreu.tarea2.msvcprocedimientos.dto.IntervinienteDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Cliente feign para el microservicio de intervinientes (msvc-intervinientes) que se encuentra en el puerto 8002
@FeignClient(name = "msvc-intervinientes", url="localhost:8002")
public interface IntervinienteClientRest {

    // Realiza una petición GET al microservicio de intervinientes para obtener un interviniente por su id
    @GetMapping("/{id}")
    IntervinienteDTO getInterviniente(@PathVariable Long id);

    // Realiza una petición GET al microservicio de intervinientes para obtener todos los intervinientes
    @GetMapping("/procedimiento/{idProcedimiento}")
    List<IntervinienteDTO> getIntervinientesByIdProcedimiento(@PathVariable Long idProcedimiento);

    // Realiza una petición POST al microservicio de intervinientes para guardar un interviniente
    @PostMapping
    IntervinienteDTO saveInterviniente(@RequestBody IntervinienteDTO intervinienteDTO);

    // Realiza una petición PUT al microservicio de intervinientes para actualizar un interviniente
    @PutMapping("/{id}")
    IntervinienteDTO updateInterviniente(@RequestBody IntervinienteDTO intervinienteDTO, @PathVariable Long id);

    // Realiza una petición DELETE al microservicio de intervinientes para eliminar un interviniente por su id
    @DeleteMapping("/{id}")
    void deleteInterviniente(@PathVariable Long id);


}
