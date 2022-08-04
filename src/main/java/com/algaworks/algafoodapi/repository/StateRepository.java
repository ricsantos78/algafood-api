package com.algaworks.algafoodapi.repository;

import com.algaworks.algafoodapi.domain.model.StateModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Controller;

import java.util.UUID;

@Controller
public interface StateRepository extends JpaRepository<StateModel, UUID> {
}
