package com.algaworks.algafoodapi.services.impl;

import com.algaworks.algafoodapi.domain.model.KitchenModel;
import com.algaworks.algafoodapi.repository.KitchenRepository;
import com.algaworks.algafoodapi.services.KitchenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class KitchenServiceImpl implements KitchenService {

    private final KitchenRepository kitchenRepository;

    @Override
    public List<KitchenModel> findAll() {
        return kitchenRepository.findAll();
    }

    @Override
    public Optional<KitchenModel> findById(UUID id) {
        return kitchenRepository.findById(id);
    }

    @Override
    @Transactional
    public KitchenModel save(KitchenModel kitchenModel) {
        return kitchenRepository.save(kitchenModel);
    }

    @Override
    public Boolean existsByName(String name) {
        return kitchenRepository.existsByName(name);
    }

    @Override
    @Transactional
    public void delete(KitchenModel kitchenModel) {
        kitchenRepository.delete(kitchenModel);
    }


}
