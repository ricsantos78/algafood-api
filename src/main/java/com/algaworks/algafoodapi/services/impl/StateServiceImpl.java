package com.algaworks.algafoodapi.services.impl;

import com.algaworks.algafoodapi.domain.model.RestaurantModel;
import com.algaworks.algafoodapi.domain.model.StateModel;
import com.algaworks.algafoodapi.repository.StateRepository;
import com.algaworks.algafoodapi.services.StateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class StateServiceImpl implements StateService {

    private final StateRepository stateRepository;

    @Override
    public List<StateModel> findAll() {
        return stateRepository.findAll();
    }

    @Override
    public Optional<StateModel> findById(UUID id) {
        return stateRepository.findById(id);
    }

    @Override
    public Boolean existsByName(String name) {
        return stateRepository.existsByName(name);
    }

    @Override
    public StateModel save(StateModel stateModel) {
        return stateRepository.save(stateModel);
    }

    @Override
    public void delete(StateModel stateModel) {
        stateRepository.delete(stateModel);
    }
}
