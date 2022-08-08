package com.algaworks.algafoodapi.services;

import com.algaworks.algafoodapi.domain.model.RestaurantModel;
import com.algaworks.algafoodapi.domain.model.StateModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface StateService {
    List<StateModel> findAll();
    Optional<StateModel> findById(UUID id);
    Boolean existsByName(String name);
    StateModel save(StateModel stateModel);
    void delete(StateModel stateModel);
}
