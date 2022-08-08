package com.algaworks.algafoodapi.controllers;

import com.algaworks.algafoodapi.domain.dto.RestaurantDto;
import com.algaworks.algafoodapi.domain.model.RestaurantModel;
import com.algaworks.algafoodapi.services.KitchenService;
import com.algaworks.algafoodapi.services.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;

    private final KitchenService kitchenService;

    @GetMapping
    public ResponseEntity<List<RestaurantModel>> findAll(){
        return ResponseEntity.ok(restaurantService.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable UUID id){
        var restaurantFindById = restaurantService.findById(id);
        return restaurantFindById
                .<ResponseEntity<Object>>map(ResponseEntity::ok)
                .orElseGet(()->ResponseEntity.status(HttpStatus.NOT_FOUND).body("Restaurant not found"));
    }
    @PostMapping
    public ResponseEntity<Object>add(@RequestBody RestaurantDto restaurantDto){
        var restaurantAlreadyExists = restaurantService.findRestaurantModelByName(restaurantDto.getName());
        if(restaurantDto.getName().equalsIgnoreCase(restaurantAlreadyExists.get().getName())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Restaurant name already exists.");
        }
        if(!restaurantService.existsRestaurantModelByKitchenId(restaurantDto.getKitchen().getId())){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Kitchen not found");
        }
        RestaurantModel restaurantModel = new RestaurantModel();
        BeanUtils.copyProperties(restaurantDto, restaurantModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(restaurantService.save(restaurantModel));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object>update(@PathVariable UUID id,
                                        @RequestBody RestaurantDto restaurantDto){
        var restaurantFindById = restaurantService.findById(id);
        var restaurantFindByName = restaurantService.findRestaurantModelByName(restaurantDto.getName());
        var kitchenFindById = kitchenService.findById(restaurantDto.getKitchen().getId());
        if(restaurantFindById.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Restaurant not Found");
        }
        if(!restaurantFindByName.isEmpty()) {
            if (restaurantDto.getName().equalsIgnoreCase(restaurantFindByName.get().getName())
                    && !id.equals(restaurantFindByName.get().getId())) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Restaurant name already exists.");
            }
        }
        if (kitchenFindById.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Kitchen not found");
        }
        RestaurantModel restaurantModel = new RestaurantModel();
        BeanUtils.copyProperties(restaurantDto, restaurantModel);
        restaurantModel.setId(restaurantFindById.get().getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(restaurantService.save(restaurantModel));
    }
}
