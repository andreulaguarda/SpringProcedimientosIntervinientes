package org.andreu.tarea2.msvcprocedimientos.service;

import org.andreu.tarea2.msvcprocedimientos.client.IntervinienteClientRest;
import org.andreu.tarea2.msvcprocedimientos.dto.ProcedimientoDTO;
import org.andreu.tarea2.msvcprocedimientos.model.DatosAutoria;
import org.andreu.tarea2.msvcprocedimientos.model.Interviniente;
import org.andreu.tarea2.msvcprocedimientos.model.entity.IdInterviniente;
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
    public List<Procedimiento> getAllProcedimientos() {

        return (List<Procedimiento>) procedimientoRepository.findAll();
    }

    @Override
    @Transactional
    public ProcedimientoDTO createProcedimiento(ProcedimientoDTO procedimientoDTO) {

        DatosAutoria datosAutoria = procedimientoDTO.getDatosAutoria();

        Procedimiento procedimiento = modelMapper.map(procedimientoDTO, Procedimiento.class);

        procedimiento.setDatosAuditoria(datosAutoria);

        List<IdInterviniente> managedIdsIntervinientes = new ArrayList<>();

        IdInterviniente tempIdInterviniente = new IdInterviniente();

        for (Interviniente interviniente : procedimientoDTO.getIntervinientes()) {
            Optional<Interviniente> existingInterviniente = Optional.ofNullable(intervinienteClientRest.getInterviniente(interviniente.getId()));
            System.out.println("existingInterviniente: " + existingInterviniente);
            if (existingInterviniente.isPresent()) {
                tempIdInterviniente.setIntervinienteId(existingInterviniente.get().getId());
            } else {
                Interviniente createdInterviniente = intervinienteClientRest.saveInterviniente(interviniente);
                tempIdInterviniente.setIntervinienteId(createdInterviniente.getId());
            }
            tempIdInterviniente.setProcedimiento(procedimiento);
            managedIdsIntervinientes.add(tempIdInterviniente);
        }

        procedimiento.setIdsIntervinientes(managedIdsIntervinientes);
        procedimientoRepository.save(procedimiento);

        return procedimientoDTO;
    }

    @Override
    public ProcedimientoDTO getProcedimiento(Long id) {


        return null;
    }

    @Override
    public ProcedimientoDTO updateProcedimiento(Long id, ProcedimientoDTO procedimientoDTO) {
        return null;
    }

    @Override
    public void deleteProcedimiento(Long id) {

    }


}
