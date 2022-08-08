package com.algaworks.algafoodapi.controllers;

import com.algaworks.algafoodapi.domain.dto.KitchenDto;
import com.algaworks.algafoodapi.domain.model.KitchenModel;
import com.algaworks.algafoodapi.services.KitchenService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RequestMapping("/kitchens")
@RestController
public class KitchenController {
    private final KitchenService kitchenService;

    @GetMapping
    public ResponseEntity<List<KitchenModel>> findAll() {
        return ResponseEntity.ok(kitchenService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable UUID id){
        var kitchenFindById = kitchenService.findById(id);
        return kitchenFindById.<ResponseEntity<Object>>map(ResponseEntity::ok)
                .orElseGet(()-> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Kitchen Not Found"));
    }

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody KitchenDto kitchenDto){
        if(Boolean.TRUE.equals(kitchenService.existsByName(kitchenDto.getName()))){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict : Kitchen name already exists");
        }
        KitchenModel kitchenModel = new KitchenModel();
        BeanUtils.copyProperties(kitchenDto,kitchenModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(kitchenService.save(kitchenModel));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable UUID id
    ,@RequestBody KitchenDto kitchenDto){
        var kitchenFindById = kitchenService.findById(id);
        if(kitchenFindById.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Kitchen Not Found");
        }
        if(Boolean.TRUE.equals(kitchenService.existsByName(kitchenDto.getName()))){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict : Kitchen name already exists");
        }

        KitchenModel kitchenModel = new KitchenModel();
        BeanUtils.copyProperties(kitchenDto, kitchenModel);
        kitchenModel.setId(kitchenFindById.get().getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(kitchenService.save(kitchenModel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable UUID id){
        try {
            var kitchenFindById = kitchenService.findById(id);
            if (kitchenFindById.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Kitchen Not Found");
            }
            kitchenService.delete(kitchenFindById.get());
            return ResponseEntity.status(HttpStatus.OK).body("Kitchen deleted successfully");
        }catch (DataIntegrityViolationException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("There is a restaurant attached to this type of Kitchen.");
        }
    }
}
