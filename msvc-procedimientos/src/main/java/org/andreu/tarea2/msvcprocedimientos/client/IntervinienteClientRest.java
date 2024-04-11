package org.andreu.tarea2.msvcprocedimientos.client;

import org.andreu.tarea2.msvcprocedimientos.model.Interviniente;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "msvc-intervinientes", url="localhost:8002")
public interface IntervinienteClientRest {

    @GetMapping("/{id}")
    Interviniente getInterviniente(@PathVariable Long id);

    @PostMapping
    Interviniente saveInterviniente(@RequestBody Interviniente interviniente);


}
