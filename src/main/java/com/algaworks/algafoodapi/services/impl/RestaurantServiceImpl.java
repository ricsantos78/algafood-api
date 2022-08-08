package com.algaworks.algafoodapi.services.impl;

import com.algaworks.algafoodapi.domain.model.RestaurantModel;
import com.algaworks.algafoodapi.repository.RestaurantRepository;
import com.algaworks.algafoodapi.services.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;

    @Override
    public List<RestaurantModel> findAll() {
        return restaurantRepository.findAll();
    }

    @Override
    public Optional<RestaurantModel> findById(UUID id) {
        return restaurantRepository.findById(id);
    }


    @Override
    @Transactional
    public RestaurantModel save(RestaurantModel restaurantModel) {
        return restaurantRepository.save(restaurantModel);
    }

    @Override
    public Optional<RestaurantModel> findRestaurantModelByName(String name) {
        return restaurantRepository.findRestaurantModelByName(name);
    }

    @Override
    public boolean existsRestaurantModelByKitchenId(UUID id) {
        return restaurantRepository.existsRestaurantModelByKitchenId(id);
    }


}
