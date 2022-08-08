package com.algaworks.algafoodapi.repository;

import com.algaworks.algafoodapi.domain.model.CityModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CityRepository extends JpaRepository<CityModel, UUID> {
    Optional<CityModel> findCityModelByName(String name);
    boolean existsCityModelByStateId(UUID id);
}
