package org.andreu.tarea2.msvcprocedimientos.service;

import org.andreu.tarea2.msvcprocedimientos.client.IntervinienteClientRest;
import org.andreu.tarea2.msvcprocedimientos.dto.IntervinienteDTO;
import org.andreu.tarea2.msvcprocedimientos.dto.ProcedimientoDTO;
import org.andreu.tarea2.msvcprocedimientos.model.DatosAutoria;
import org.andreu.tarea2.msvcprocedimientos.model.entity.Procedimiento;
import org.andreu.tarea2.msvcprocedimientos.repository.ProcedimientoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

        List<Procedimiento> procedimientos = (List<Procedimiento>) procedimientoRepository.findAll();

        return procedimientos.stream().map(procedimiento -> modelMapper.map(procedimiento, ProcedimientoDTO.class)).toList();

    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ProcedimientoDTO> findById(Long id) {

        Optional<Procedimiento> procedimiento = procedimientoRepository.findById(id);

        return procedimiento.map(value -> modelMapper.map(value, ProcedimientoDTO.class));

    }

    @Override
    public ProcedimientoDTO save(ProcedimientoDTO procedimientoDTO) {

        for (IntervinienteDTO intervinienteDTO : procedimientoDTO.getIntervinientes()) {
            intervinienteClientRest.saveInterviniente(intervinienteDTO);
        }

        Procedimiento procedimiento = modelMapper.map(procedimientoDTO, Procedimiento.class);

        procedimiento.setDatosAuditoria(procedimientoDTO.getDatosAutoria());

        ProcedimientoDTO savedProcedimientoDTO = modelMapper.map(procedimientoRepository.save(procedimiento), ProcedimientoDTO.class);

        List<IntervinienteDTO> intervinientes = intervinienteClientRest.getIntervinientesByIdProcedimiento(procedimiento.getId());

        savedProcedimientoDTO.setIntervinientes(intervinientes);

        return savedProcedimientoDTO;

    }

    @Override
    public ProcedimientoDTO update(ProcedimientoDTO procedimientoDTO) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }


}
