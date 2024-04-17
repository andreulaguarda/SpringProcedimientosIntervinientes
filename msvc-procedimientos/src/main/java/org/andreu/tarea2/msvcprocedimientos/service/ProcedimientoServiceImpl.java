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

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

// Implementación de los servicios de Procedimiento que se comunican con el repositorio de Procedimientos y el cliente de Intervinientes
@Service
public class ProcedimientoServiceImpl implements ProcedimientoService {

    @Autowired
    private ProcedimientoRepository procedimientoRepository;

    @Autowired
    private IntervinienteClientRest intervinienteClientRest;

    @Autowired
    private ModelMapper modelMapper;

    private static final String USUARIO = "andreulaguarda";

    // Se obtienen todos los procedimientos y se mapean a DTO
    @Override
    @Transactional(readOnly = true)
    public List<ProcedimientoDTO> findAll() {

        List<Procedimiento> procedimientos = procedimientoRepository.findAll();

        return procedimientos.stream().map(procedimiento -> modelMapper.map(procedimiento, ProcedimientoDTO.class)).toList();

    }

    // Se obtiene un procedimiento por su id y se mapea a DTO junto con sus intervinientes obtenidos del cliente de Intervinientes
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

    // Se guarda un procedimiento y sus intervinientes y se devuelve el procedimiento guardado mapeado a DTO
    // con sus intervinientes y sus datos de auditoria
    @Override
    @Transactional
    public ProcedimientoDTO save(ProcedimientoDTO procedimientoDTO) {

        // Se mapea el DTO a la entidad Procedimiento
        Procedimiento procedimiento = modelMapper.map(procedimientoDTO, Procedimiento.class);

        // Se añaden los datos de auditoria con la fecha y usuario actual al procedimiento antes de guardarlo
        DatosAutoria datosAutoria = new DatosAutoria();

        datosAutoria.setFechaCreacion(LocalDate.now());

        datosAutoria.setUsuarioCreacion(USUARIO);

        procedimiento.setDatosAuditoria(datosAutoria);

        ProcedimientoDTO savedProcedimientoDTO = modelMapper.map(procedimientoRepository.save(procedimiento), ProcedimientoDTO.class);

        // Se guardan los intervinientes del procedimiento en el cliente de Intervinientes
        for (IntervinienteDTO intervinienteDTO : procedimientoDTO.getIntervinientes()) {

            intervinienteDTO.setIdProcedimiento(savedProcedimientoDTO.getId());

            intervinienteClientRest.saveInterviniente(intervinienteDTO);

        }
        // Se obtienen los intervinientes guardados y se añaden al procedimiento DTO que se devuelve
        List<IntervinienteDTO> intervinientes = intervinienteClientRest.getIntervinientesByIdProcedimiento(procedimiento.getId());

        savedProcedimientoDTO.setIntervinientes(intervinientes);

        return savedProcedimientoDTO;

    }

    //
    @Override
    @Transactional
    public ProcedimientoDTO update(ProcedimientoDTO procedimientoDTO) {

        // Se obtienen los intervinientes anteriores y se comparan con los actuales
        List<IntervinienteDTO> intervinientesAnteriores = intervinienteClientRest.getIntervinientesByIdProcedimiento(procedimientoDTO.getId());

        List<IntervinienteDTO> intervinientesNuevos = procedimientoDTO.getIntervinientes();

        // Se eliminan los intervinientes anteriores que ya no estan en la nueva lista de intervinientes
        for (IntervinienteDTO intervinienteDTO : intervinientesAnteriores) {
            int index = intervinientesNuevos.indexOf(intervinienteDTO);
            if (index == -1) {
                intervinienteClientRest.deleteInterviniente(intervinienteDTO.getId());
            } else {

                // Se actualizan los intervinientes que ya estaban en la lista de intervinientes
                IntervinienteDTO intervinienteActualizado = intervinientesNuevos.get(index);
                intervinienteActualizado.setId(intervinienteDTO.getId());
                intervinienteActualizado.setIdProcedimiento(intervinienteDTO.getIdProcedimiento());
                DatosAutoria datosAutoria = intervinienteDTO.getDatosAutoria();
                datosAutoria.setFechaModificacion(LocalDate.now());
                datosAutoria.setUsuarioModificacion(USUARIO);
                intervinienteActualizado.setDatosAutoria(datosAutoria);
                intervinienteClientRest.updateInterviniente(intervinienteActualizado, intervinienteActualizado.getId());
                intervinientesNuevos.remove(intervinienteActualizado);
            }
        }

        // Se añaden los intervinientes nuevos que no estaban en la lista de intervinientes anteriores
        // y se guardan en el cliente de Intervinientes con el id del procedimiento actual y los datos de auditoria
        procedimientoDTO.setIntervinientes(intervinientesNuevos);

        Procedimiento procedimiento = modelMapper.map(procedimientoDTO, Procedimiento.class);

        Procedimiento existingProcedimiento = procedimientoRepository.findById(procedimiento.getId()).get();

        DatosAutoria datosAutoria = existingProcedimiento.getDatosAuditoria();

        datosAutoria.setFechaModificacion(LocalDate.now());

        datosAutoria.setUsuarioModificacion(USUARIO);

        procedimiento.setDatosAuditoria(datosAutoria);

        ProcedimientoDTO savedProcedimientoDTO = modelMapper.map(procedimientoRepository.save(procedimiento), ProcedimientoDTO.class);

        for (IntervinienteDTO intervinienteDTO : procedimientoDTO.getIntervinientes()) {

            intervinienteDTO.setIdProcedimiento(savedProcedimientoDTO.getId());

            intervinienteClientRest.saveInterviniente(intervinienteDTO);

        }

        // Se obtienen los intervinientes guardados y se añaden al procedimiento DTO que se devuelve
        List<IntervinienteDTO> intervinientes = intervinienteClientRest.getIntervinientesByIdProcedimiento(procedimiento.getId());

        savedProcedimientoDTO.setIntervinientes(intervinientes);

        return savedProcedimientoDTO;

    }


    // Se eliminan un procedimiento por su id
    @Override
    @Transactional
    public void deleteById(Long id) {

        procedimientoRepository.deleteById(id);

    }

    // Se eliminan los intervinientes relacionados con un procedimiento por su id
    @Override
    @Transactional
    public void deleteRelatedIntervinientes(Long id) {

        List<IntervinienteDTO> intervinientes = intervinienteClientRest.getIntervinientesByIdProcedimiento(id);

        for (IntervinienteDTO intervinienteDTO : intervinientes) {
            intervinienteClientRest.deleteInterviniente(intervinienteDTO.getId());
        }
    }

}
