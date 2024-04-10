package org.andreu.tarea2.msvcintervinientes.service;

import org.andreu.tarea2.msvcintervinientes.model.entity.Interviniente;
import org.andreu.tarea2.msvcintervinientes.repository.IntervinienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class IntervinienteServiceImpl implements IntervinienteService {

    @Autowired
    private IntervinienteRepository intervinienteRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Interviniente> findAll() {
        return intervinienteRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Interviniente> findById(Long id) {
        return intervinienteRepository.findById(id);
    }

    @Override
    @Transactional
    public Interviniente save(Interviniente interviniente) {
        return intervinienteRepository.save(interviniente);
    }

    @Override
    @Transactional
    public Interviniente update(Interviniente interviniente) {
        return intervinienteRepository.save(interviniente);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        intervinienteRepository.deleteById(id);
    }
}