package org.andreu.tarea2.msvcintervinientes.service;

import org.andreu.tarea2.msvcintervinientes.dto.IntervinienteDTO;
import org.andreu.tarea2.msvcintervinientes.model.entity.Interviniente;
import org.andreu.tarea2.msvcintervinientes.repository.IntervinienteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class IntervinienteServiceImpl implements IntervinienteService {

    @Autowired
    private IntervinienteRepository intervinienteRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Transactional(readOnly = true)
    public List<IntervinienteDTO> findAll() {

        List<Interviniente> intervinientes = intervinienteRepository.findAll();

        return intervinientes.stream().map(interviniente -> modelMapper.map(interviniente, IntervinienteDTO.class)).toList();

    }

    @Override
    @Transactional(readOnly = true)
    public Optional<IntervinienteDTO> findById(Long id) {

        Optional<Interviniente> interviniente = intervinienteRepository.findById(id);

        return interviniente.map(value -> modelMapper.map(value, IntervinienteDTO.class));

    }

    @Override
    @Transactional
    public IntervinienteDTO save(IntervinienteDTO intervinienteDTO) {

        Interviniente interviniente = modelMapper.map(intervinienteDTO, Interviniente.class);

        return modelMapper.map(intervinienteRepository.save(interviniente), IntervinienteDTO.class);
    }

    @Override
    @Transactional
    public IntervinienteDTO update(IntervinienteDTO intervinienteDTO) {

            Interviniente interviniente = modelMapper.map(intervinienteDTO, Interviniente.class);

            return modelMapper.map(intervinienteRepository.save(interviniente), IntervinienteDTO.class);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        intervinienteRepository.deleteById(id);
    }
}