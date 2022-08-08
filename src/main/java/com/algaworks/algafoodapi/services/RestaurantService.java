package com.algaworks.algafoodapi.services;

import com.algaworks.algafoodapi.domain.model.RestaurantModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RestaurantService {
    List<RestaurantModel> findAll();
    Optional<RestaurantModel> findById(UUID id);
    RestaurantModel save(RestaurantModel restaurantModel);
    Optional<RestaurantModel> findRestaurantModelByName(String name);
    boolean existsRestaurantModelByKitchenId(UUID id);
}
