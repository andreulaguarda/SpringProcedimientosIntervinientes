package org.andreu.tarea2.msvcprocedimientos.client;

import org.andreu.tarea2.msvcprocedimientos.dto.IntervinienteDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "msvc-intervinientes", url="localhost:8002")
public interface IntervinienteClientRest {

    @GetMapping("/{id}")
    IntervinienteDTO getInterviniente(@PathVariable Long id);

    @GetMapping("/procedimiento/{idProcedimiento}")
    List<IntervinienteDTO> getIntervinientesByIdProcedimiento(@PathVariable Long idProcedimiento);

    @PostMapping
    IntervinienteDTO saveInterviniente(@RequestBody IntervinienteDTO intervinienteDTO);

    @DeleteMapping("/{id}")
    void deleteInterviniente(@PathVariable Long id);


}
