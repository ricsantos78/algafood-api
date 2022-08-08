package com.algaworks.algafoodapi.repository;

import com.algaworks.algafoodapi.domain.model.RestaurantModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RestaurantRepository extends JpaRepository<RestaurantModel, UUID> {
    Optional<RestaurantModel> findRestaurantModelByName(String name);
    boolean existsRestaurantModelByKitchenId(UUID id);
}
