package org.andreu.tarea2.msvcintervinientes.service;

import org.andreu.tarea2.msvcintervinientes.dto.IntervinienteDTO;
import org.andreu.tarea2.msvcintervinientes.model.DatosAutoria;
import org.andreu.tarea2.msvcintervinientes.model.entity.Interviniente;
import org.andreu.tarea2.msvcintervinientes.repository.IntervinienteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

// Se implementan los métodos de la interfaz IntervinienteService
@Service
public class IntervinienteServiceImpl implements IntervinienteService {

    // Se inyecta el repositorio de Interviniente
    @Autowired
    private IntervinienteRepository intervinienteRepository;

    // Se inyecta el modelMapper
    @Autowired
    private ModelMapper modelMapper;

    // Devuelve una lista con todos los intervinientes de la base de datos mapeados a IntervinienteDTO
    @Override
    @Transactional(readOnly = true)
    public List<IntervinienteDTO> findAll() {

        List<Interviniente> intervinientes = intervinienteRepository.findAll();

        return intervinientes.stream().map(interviniente -> modelMapper.map(interviniente, IntervinienteDTO.class)).toList();

    }

    // Devuelve una lista con todos los intervinientes relacionados con un procedimiento mapeados a IntervinienteDTO
    @Override
    @Transactional(readOnly = true)
    public List<IntervinienteDTO> findbyIdProcedimiento(Long idProcedimiento) {

        List<Interviniente> intervinientes = intervinienteRepository.findByIdProcedimiento(idProcedimiento);

        return intervinientes.stream().map(interviniente -> modelMapper.map(interviniente, IntervinienteDTO.class)).toList();

    }

    // Devuelve un interviniente por su id mapeado a IntervinienteDTO
    @Override
    @Transactional(readOnly = true)
    public Optional<IntervinienteDTO> findById(Long id) {

        Optional<Interviniente> interviniente = intervinienteRepository.findById(id);

        return interviniente.map(value -> modelMapper.map(value, IntervinienteDTO.class));

    }

    // Guarda un interviniente en la base de datos y lo devuelve mapeado a IntervinienteDTO
    // Se añade la fecha de creación actual y el usuario que lo ha creado
    @Override
    @Transactional
    public IntervinienteDTO save(IntervinienteDTO intervinienteDTO) {

        Interviniente interviniente = modelMapper.map(intervinienteDTO, Interviniente.class);

        DatosAutoria datosAutoria = new DatosAutoria();

        datosAutoria.setFechaCreacion(LocalDate.now());

        datosAutoria.setUsuarioCreacion("andreulaguarda");

        interviniente.setDatosAutoria(datosAutoria);

        return modelMapper.map(intervinienteRepository.save(interviniente), IntervinienteDTO.class);
    }

    // Actualiza un interviniente en la base de datos y lo devuelve mapeado a IntervinienteDTO
    // Se añade la fecha de modificación actual y el usuario que lo ha modificado
    @Override
    @Transactional
    public IntervinienteDTO update(IntervinienteDTO intervinienteDTO) {

        Interviniente interviniente = modelMapper.map(intervinienteDTO, Interviniente.class);

        Optional<Interviniente> existingInterviniente = intervinienteRepository.findById(interviniente.getId());

        if (existingInterviniente.isPresent()) {

            DatosAutoria datosAutoria = existingInterviniente.get().getDatosAutoria();

            datosAutoria.setFechaModificacion(LocalDate.now());

            datosAutoria.setUsuarioModificacion("andreulaguarda");

            interviniente.setDatosAutoria(datosAutoria);
        }

        return modelMapper.map(intervinienteRepository.save(interviniente), IntervinienteDTO.class);
    }

    // Elimina un interviniente de la base de datos por su id
    @Override
    @Transactional
    public void deleteById(Long id) {
        intervinienteRepository.deleteById(id);
    }
}