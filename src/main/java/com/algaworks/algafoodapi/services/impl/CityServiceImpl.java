package com.algaworks.algafoodapi.services.impl;

import com.algaworks.algafoodapi.domain.model.CityModel;
import com.algaworks.algafoodapi.repository.CityRepository;
import com.algaworks.algafoodapi.services.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;

    @Override
    public List<CityModel> findAll() {
        return cityRepository.findAll();
    }

    @Override
    public Optional<CityModel> findById(UUID id) {
        return cityRepository.findById(id);
    }

    @Override
    public CityModel save(CityModel cityModel) {
        return cityRepository.save(cityModel);
    }

    @Override
    public Optional<CityModel> findCityModelByName(String name) {
        return cityRepository.findCityModelByName(name);
    }

    @Override
    public boolean existsCityModelByStateId(UUID id) {
        return cityRepository.existsCityModelByStateId(id);
    }
}
