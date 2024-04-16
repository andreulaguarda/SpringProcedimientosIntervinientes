package org.andreu.tarea2.msvcprocedimientos.client;

import org.andreu.tarea2.msvcprocedimientos.dto.IntervinienteDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class IntervinienteClientRestTest {

    private IntervinienteClientRest intervinienteClientRest;

    private IntervinienteDTO intervinienteDTO1 = new IntervinienteDTO();

    private IntervinienteDTO intervinienteDTO2 = new IntervinienteDTO();

    @BeforeEach
    void setUp() {

        intervinienteDTO1.setId(1L);
        intervinienteDTO1.setNombre("Interviniente 1");
        intervinienteDTO1.setTipoIntervencion("Tipo 1");

        intervinienteDTO2.setId(2L);
        intervinienteDTO2.setNombre("Interviniente 2");
        intervinienteDTO2.setTipoIntervencion("Tipo 2");

        intervinienteClientRest = Mockito.mock(IntervinienteClientRest.class);
    }

    @Test
    void getInterviniente() {

        when(intervinienteClientRest.getInterviniente(1L)).thenReturn(intervinienteDTO1);

        IntervinienteDTO intervinienteDTO = intervinienteClientRest.getInterviniente(1L);

        assertEquals(intervinienteDTO1, intervinienteDTO);

    }

    @Test
    void getIntervinientesByIdProcedimiento() {

        when(intervinienteClientRest.getIntervinientesByIdProcedimiento(1L))
                .thenReturn(List.of(intervinienteDTO1, intervinienteDTO2));

        assertEquals(List.of(intervinienteDTO1, intervinienteDTO2), intervinienteClientRest
                .getIntervinientesByIdProcedimiento(1L));
    }

    @Test
    void saveInterviniente() {

        when(intervinienteClientRest.saveInterviniente(intervinienteDTO1)).thenReturn(intervinienteDTO1);

        IntervinienteDTO intervinienteDTO = intervinienteClientRest.saveInterviniente(intervinienteDTO1);

        assertEquals(intervinienteDTO1, intervinienteDTO);
    }

    @Test
    void updateInterviniente() {

        when(intervinienteClientRest.updateInterviniente(intervinienteDTO1, 1L)).thenReturn(intervinienteDTO1);

        IntervinienteDTO intervinienteDTO = intervinienteClientRest.updateInterviniente(intervinienteDTO1, 1L);

        assertEquals(intervinienteDTO1, intervinienteDTO);
    }

    @Test
    void deleteInterviniente() {

        intervinienteClientRest.deleteInterviniente(1L);

        Mockito.verify(intervinienteClientRest, Mockito.times(1)).deleteInterviniente(1L);
    }
}