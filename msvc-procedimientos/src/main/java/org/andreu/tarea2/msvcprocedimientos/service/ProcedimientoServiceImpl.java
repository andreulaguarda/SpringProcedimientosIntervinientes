package org.andreu.tarea2.msvcprocedimientos.service;

import org.andreu.tarea2.msvcprocedimientos.client.IntervinienteClientRest;
import org.andreu.tarea2.msvcprocedimientos.dto.IntervinienteDTO;
import org.andreu.tarea2.msvcprocedimientos.dto.ProcedimientoDTO;
import org.andreu.tarea2.msvcprocedimientos.model.entity.Procedimiento;
import org.andreu.tarea2.msvcprocedimientos.repository.ProcedimientoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProcedimientoServiceImpl implements ProcedimientoService {

    @Autowired
    private ProcedimientoRepository procedimientoRepository;

    @Autowired
    private IntervinienteClientRest intervinienteClientRest;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Transactional(readOnly = true)
    public List<ProcedimientoDTO> findAll() {

        List<Procedimiento> procedimientos = procedimientoRepository.findAll();

        return procedimientos.stream().map(procedimiento -> modelMapper.map(procedimiento, ProcedimientoDTO.class)).toList();

    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ProcedimientoDTO> findById(Long id) {

        Optional<Procedimiento> procedimiento = procedimientoRepository.findById(id);

        if (procedimiento.isPresent()) {
            ProcedimientoDTO procedimientoDTO = modelMapper.map(procedimiento.get(), ProcedimientoDTO.class);

            List<IntervinienteDTO> intervinientes = intervinienteClientRest.getIntervinientesByIdProcedimiento(procedimiento.get().getId());

            procedimientoDTO.setIntervinientes(intervinientes);

            return Optional.of(procedimientoDTO);
        }

        return Optional.empty();

    }

    @Override
    @Transactional
    public ProcedimientoDTO save(ProcedimientoDTO procedimientoDTO) {

        Procedimiento procedimiento = modelMapper.map(procedimientoDTO, Procedimiento.class);

        procedimiento.setDatosAuditoria(procedimientoDTO.getDatosAutoria());

        ProcedimientoDTO savedProcedimientoDTO = modelMapper.map(procedimientoRepository.save(procedimiento), ProcedimientoDTO.class);

        for (IntervinienteDTO intervinienteDTO : procedimientoDTO.getIntervinientes()) {

            intervinienteDTO.setIdProcedimiento(savedProcedimientoDTO.getId());

            intervinienteClientRest.saveInterviniente(intervinienteDTO);

        }

        List<IntervinienteDTO> intervinientes = intervinienteClientRest.getIntervinientesByIdProcedimiento(procedimiento.getId());

        savedProcedimientoDTO.setIntervinientes(intervinientes);

        return savedProcedimientoDTO;

    }


    @Override
    @Transactional
    public void deleteById(Long id) {

        procedimientoRepository.deleteById(id);

    }

    @Override
    @Transactional
    public void deleteRelatedIntervinientes(Long id) {

        List<IntervinienteDTO> intervinientes = intervinienteClientRest.getIntervinientesByIdProcedimiento(id);

        for (IntervinienteDTO intervinienteDTO : intervinientes) {
            intervinienteClientRest.deleteInterviniente(intervinienteDTO.getId());
        }
    }

}
