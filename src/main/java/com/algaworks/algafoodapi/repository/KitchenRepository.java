package com.algaworks.algafoodapi.repository;

import com.algaworks.algafoodapi.domain.model.KitchenModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface KitchenRepository extends JpaRepository<KitchenModel, UUID> {
    Boolean existsByName(String name);
}

