package com.algaworks.algafoodapi.services;

import com.algaworks.algafoodapi.domain.model.StateModel;

import java.util.List;

public interface StateService {
    List<StateModel> findAll();
}
