package org.andreu.tarea2.msvcintervinientes.service;

import org.andreu.tarea2.msvcintervinientes.dto.IntervinienteDTO;
import org.andreu.tarea2.msvcintervinientes.model.DatosAutoria;
import org.andreu.tarea2.msvcintervinientes.model.entity.Interviniente;
import org.andreu.tarea2.msvcintervinientes.repository.IntervinienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class IntervinienteServiceImplTest {

    @InjectMocks
    private IntervinienteServiceImpl intervinienteServiceImpl;

    @Mock
    private IntervinienteRepository intervinienteRepository;

    @Mock
    private ModelMapper modelMapper;

    private Interviniente interviniente1 = new Interviniente();

    private Interviniente interviniente2 = new Interviniente();

    private IntervinienteDTO intervinienteDTO1 = new IntervinienteDTO();

    private IntervinienteDTO intervinienteDTO2 = new IntervinienteDTO();

    private DatosAutoria datosAutoria = new DatosAutoria();

    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);

        datosAutoria.setUsuarioCreacion("Test");
        datosAutoria.setFechaCreacion(LocalDate.now());
        datosAutoria.setUsuarioModificacion("Test");
        datosAutoria.setFechaModificacion(LocalDate.now());

        interviniente1.setId(1L);
        interviniente1.setNombre("Interviniente 1");
        interviniente1.setIdProcedimiento(1L);
        interviniente1.setTipoIntervencion("Tipo 1");
        interviniente1.setDatosAutoria(datosAutoria);

        interviniente2.setId(2L);
        interviniente2.setNombre("Interviniente 2");
        interviniente2.setIdProcedimiento(1L);
        interviniente2.setTipoIntervencion("Tipo 2");
        interviniente2.setDatosAutoria(datosAutoria);

        intervinienteDTO1.setId(1L);
        intervinienteDTO1.setNombre("Interviniente 1");
        intervinienteDTO1.setIdProcedimiento(1L);
        intervinienteDTO1.setTipoIntervencion("Tipo 1");
        intervinienteDTO1.setDatosAutoria(datosAutoria);

        intervinienteDTO2.setId(2L);
        intervinienteDTO2.setNombre("Interviniente 2");
        intervinienteDTO2.setIdProcedimiento(1L);
        intervinienteDTO2.setTipoIntervencion("Tipo 2");
        intervinienteDTO2.setDatosAutoria(datosAutoria);

    }

    @Test
    void findAll() {

        when(intervinienteRepository.findAll()).thenReturn(List.of(interviniente1, interviniente2));
        when(modelMapper.map(interviniente1, IntervinienteDTO.class)).thenReturn(intervinienteDTO1);
        when(modelMapper.map(interviniente2, IntervinienteDTO.class)).thenReturn(intervinienteDTO2);

        List<IntervinienteDTO> intervinientes = intervinienteServiceImpl.findAll();

        assertNotNull(intervinientes);
        assertEquals(2, intervinientes.size());
        assertEquals(intervinienteDTO1, intervinientes.get(0));

        verify(intervinienteRepository, times(1)).findAll();
        verify(modelMapper, times(2)).map(any(), any());
    }

    @Test
    void findbyIdProcedimiento() {

        when(intervinienteRepository.findByIdProcedimiento(1L)).thenReturn(List.of(interviniente1, interviniente2));
        when(modelMapper.map(interviniente1, IntervinienteDTO.class)).thenReturn(intervinienteDTO1);
        when(modelMapper.map(interviniente2, IntervinienteDTO.class)).thenReturn(intervinienteDTO2);

        List<IntervinienteDTO> intervinientes = intervinienteServiceImpl.findbyIdProcedimiento(1L);

        assertNotNull(intervinientes);
        assertEquals(2, intervinientes.size());
        assertEquals(intervinienteDTO1, intervinientes.get(0));

        verify(intervinienteRepository, times(1)).findByIdProcedimiento(1L);
        verify(modelMapper, times(2)).map(any(), any());


    }

    @Test
    void findById() {

        when(intervinienteRepository.findById(1L)).thenReturn(java.util.Optional.of(interviniente1));
        when(modelMapper.map(interviniente1, IntervinienteDTO.class)).thenReturn(intervinienteDTO1);

        IntervinienteDTO interviniente = intervinienteServiceImpl.findById(1L).get();

        assertNotNull(interviniente);
        assertEquals(intervinienteDTO1, interviniente);

        verify(intervinienteRepository, times(1)).findById(1L);
        verify(modelMapper, times(1)).map(any(), any());
    }

    @Test
    void save() {

        when(modelMapper.map(intervinienteDTO1, Interviniente.class)).thenReturn(interviniente1);
        when(intervinienteRepository.save(interviniente1)).thenReturn(interviniente1);
        when(modelMapper.map(interviniente1, IntervinienteDTO.class)).thenReturn(intervinienteDTO1);

        IntervinienteDTO interviniente = intervinienteServiceImpl.save(intervinienteDTO1);

        assertNotNull(interviniente);
        assertEquals(intervinienteDTO1, interviniente);

        verify(intervinienteRepository, times(1)).save(any());

    }

    @Test
    void update() {

        when(intervinienteRepository.findById(1L)).thenReturn(java.util.Optional.of(interviniente1));
        when(modelMapper.map(intervinienteDTO1, Interviniente.class)).thenReturn(interviniente1);
        when(intervinienteRepository.save(interviniente1)).thenReturn(interviniente1);
        when(modelMapper.map(interviniente1, IntervinienteDTO.class)).thenReturn(intervinienteDTO1);

        IntervinienteDTO interviniente = intervinienteServiceImpl.update(intervinienteDTO1);

        assertNotNull(interviniente);
        assertEquals(intervinienteDTO1, interviniente);

        verify(intervinienteRepository, times(1)).findById(1L);
        verify(intervinienteRepository, times(1)).save(any());

    }

    @Test
    void deleteById() {

        intervinienteServiceImpl.deleteById(1L);

        verify(intervinienteRepository, times(1)).deleteById(1L);
    }
}