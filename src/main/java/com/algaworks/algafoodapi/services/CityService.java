package com.algaworks.algafoodapi.services;

import com.algaworks.algafoodapi.domain.model.CityModel;
import com.algaworks.algafoodapi.domain.model.RestaurantModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CityService {
    List<CityModel> findAll();
    Optional<CityModel> findById(UUID id);
    CityModel save(CityModel cityModel);
    Optional<CityModel> findCityModelByName(String name);
    boolean existsCityModelByStateId(UUID id);
}
