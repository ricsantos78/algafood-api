package com.algaworks.algafoodapi.services.impl;

import com.algaworks.algafoodapi.domain.model.StateModel;
import com.algaworks.algafoodapi.repository.StateRepository;
import com.algaworks.algafoodapi.services.StateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class StateServiceImpl implements StateService {

    private final StateRepository stateRepository;

    @Override
    public List<StateModel> findAll() {
        return stateRepository.findAll();
    }
}
