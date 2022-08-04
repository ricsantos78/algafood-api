package com.algaworks.algafoodapi.services;

import com.algaworks.algafoodapi.domain.model.KitchenModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface KitchenService {
    List<KitchenModel> findAll();
    Optional<KitchenModel> findById(UUID id);
    KitchenModel save(KitchenModel kitchenModel);

    Boolean existsByName(String name);

    void delete(KitchenModel kitchenModel);
}
