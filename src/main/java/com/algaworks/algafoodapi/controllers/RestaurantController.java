package com.algaworks.algafoodapi.controllers;

import com.algaworks.algafoodapi.domain.model.RestaurantModel;
import com.algaworks.algafoodapi.services.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;

    @GetMapping
    public ResponseEntity<List<RestaurantModel>> findAll(){
        List<RestaurantModel> restaurantModelList = restaurantService.findAll();
        return ResponseEntity.ok(restaurantModelList);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable UUID id){
        Optional<RestaurantModel> restaurantModelOptional = restaurantService.findById(id);
        return restaurantModelOptional
                .<ResponseEntity<Object>>map(ResponseEntity::ok)
                .orElseGet(()->ResponseEntity.status(HttpStatus.NOT_FOUND).body("Restaurant not found"));
    }
}
