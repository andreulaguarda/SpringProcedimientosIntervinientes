package org.andreu.tarea2.msvcprocedimientos.service;

import org.andreu.tarea2.msvcprocedimientos.client.IntervinienteClientRest;
import org.andreu.tarea2.msvcprocedimientos.dto.IntervinienteDTO;
import org.andreu.tarea2.msvcprocedimientos.dto.ProcedimientoDTO;
import org.andreu.tarea2.msvcprocedimientos.model.DatosAutoria;
import org.andreu.tarea2.msvcprocedimientos.model.entity.Procedimiento;
import org.andreu.tarea2.msvcprocedimientos.repository.ProcedimientoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProcedimientoServiceImplTest {

    @Mock
    private ProcedimientoRepository procedimientoRepository;

    @Mock
    private IntervinienteClientRest intervinienteClientRest;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private ProcedimientoServiceImpl procedimientoService;

    private ProcedimientoDTO procedimientoDTO1 = new ProcedimientoDTO();

    private Procedimiento procedimiento1 = new Procedimiento();

    private ProcedimientoDTO procedimientoDTO2 = new ProcedimientoDTO();

    private Procedimiento procedimiento2 = new Procedimiento();

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

        procedimientoDTO1.setId(1L);
        procedimientoDTO1.setNumProcedimiento(1);
        procedimientoDTO1.setAnyo(2021);
        procedimientoDTO1.setIntervinientes(List.of(intervinienteDTO1));
        procedimientoDTO1.setDatosAutoria(datosAutoria);

        procedimientoDTO2.setId(2L);
        procedimientoDTO2.setNumProcedimiento(2);
        procedimientoDTO2.setAnyo(2022);
        procedimientoDTO2.setIntervinientes(List.of(intervinienteDTO1));

        procedimiento1.setId(1L);
        procedimiento1.setNumProcedimiento(1);
        procedimiento1.setAnyo(2021);
        procedimiento1.setDatosAuditoria(datosAutoria);

        procedimiento2.setId(2L);
        procedimiento2.setNumProcedimiento(2);
        procedimiento2.setAnyo(2022);
        procedimiento2.setDatosAuditoria(datosAutoria);


    }

    @Test
    void testFindAll() {

        when(procedimientoRepository.findAll()).thenReturn(List.of(procedimiento1, procedimiento2));
        when(modelMapper.map(procedimiento1, ProcedimientoDTO.class)).thenReturn(procedimientoDTO1);
        when(modelMapper.map(procedimiento2, ProcedimientoDTO.class)).thenReturn(procedimientoDTO2);

        List<ProcedimientoDTO> procedimientosDTO = procedimientoService.findAll();

        verify(procedimientoRepository, times(1)).findAll();

        assertEquals(2, procedimientosDTO.size());

        assertEquals(procedimientoDTO1, procedimientosDTO.get(0));
        assertEquals(procedimientoDTO2, procedimientosDTO.get(1));
    }

    @Test
    void testFindById() {

        when(procedimientoRepository.findById(1L)).thenReturn(Optional.of(procedimiento1));
        when(modelMapper.map(procedimiento1, ProcedimientoDTO.class)).thenReturn(procedimientoDTO1);

        Optional<ProcedimientoDTO> procedimientoDTO = procedimientoService.findById(1L);

        Optional<ProcedimientoDTO> expectedProcedimientoDTO = Optional.of(procedimientoDTO1);

        verify(procedimientoRepository, times(1)).findById(1L);

        assertEquals(expectedProcedimientoDTO, procedimientoDTO);

    }

    @Test
    void testSave() {

        when(modelMapper.map(procedimientoDTO1, Procedimiento.class)).thenReturn(procedimiento1);
        when(modelMapper.map(procedimiento1, ProcedimientoDTO.class)).thenReturn(procedimientoDTO1);
        when(procedimientoRepository.save(procedimiento1)).thenReturn(procedimiento1);
        when(intervinienteClientRest.saveInterviniente(intervinienteDTO1)).thenReturn(intervinienteDTO1);
        when(intervinienteClientRest.saveInterviniente(intervinienteDTO2)).thenReturn(intervinienteDTO2);
        when(intervinienteClientRest.getIntervinientesByIdProcedimiento(procedimiento1.getId()))
                .thenReturn(List.of(intervinienteDTO1, intervinienteDTO2));

        ProcedimientoDTO savedProcedimientoDTO = procedimientoService.save(procedimientoDTO1);

        verify(procedimientoRepository, times(1)).save(procedimiento1);
        verify(intervinienteClientRest, times(1))
                .getIntervinientesByIdProcedimiento(procedimiento1.getId());

        assertEquals(procedimientoDTO1, savedProcedimientoDTO);
    }

    @Test
    void testUpdate() {

        when(procedimientoRepository.findById(1L)).thenReturn(Optional.of(procedimiento1));
        when(modelMapper.map(procedimientoDTO1, Procedimiento.class)).thenReturn(procedimiento1);
        when(modelMapper.map(procedimiento1, ProcedimientoDTO.class)).thenReturn(procedimientoDTO1);
        when(procedimientoRepository.save(procedimiento1)).thenReturn(procedimiento1);
        when(intervinienteClientRest.saveInterviniente(intervinienteDTO1)).thenReturn(intervinienteDTO1);
        when(intervinienteClientRest.saveInterviniente(intervinienteDTO2)).thenReturn(intervinienteDTO2);
        when(intervinienteClientRest.getIntervinientesByIdProcedimiento(procedimiento1.getId())).thenReturn(List.of(intervinienteDTO2));

        ProcedimientoDTO updatedProcedimientoDTO =  procedimientoService.update(procedimientoDTO1);

        assertEquals(procedimientoDTO1, updatedProcedimientoDTO);

    }

    @Test
    void testDeleteById() {

        procedimientoService.deleteById(1L);

        verify(procedimientoRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteRelatedIntervinientes() {

        when(intervinienteClientRest.getIntervinientesByIdProcedimiento(1L)).thenReturn(List.of(intervinienteDTO1, intervinienteDTO2));

        procedimientoService.deleteRelatedIntervinientes(1L);

        verify(intervinienteClientRest, times(1)).deleteInterviniente(1L);
        verify(intervinienteClientRest, times(1)).deleteInterviniente(2L);

    }
}