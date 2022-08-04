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
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@RequestMapping("/kitchens")
@RestController
public class KitchenController {
    private final KitchenService kitchenService;

    @GetMapping
    public ResponseEntity<List<KitchenModel>> findAll() {
        List<KitchenModel> kitchens = kitchenService.findAll();
        return ResponseEntity.ok(kitchens);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable UUID id){
        Optional<KitchenModel> kitchenModelOptional = kitchenService.findById(id);
        return kitchenModelOptional.<ResponseEntity<Object>>map(ResponseEntity::ok)
                .orElseGet(()-> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Kitchen Not Found"));
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody KitchenDto kitchenDto){
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
        Optional<KitchenModel> kitchenOpt = kitchenService.findById(id);
        if(kitchenOpt.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Kitchen Not Found");
        }
        if(Boolean.TRUE.equals(kitchenService.existsByName(kitchenDto.getName()))){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict : Kitchen name already exists");
        }

        KitchenModel kitchenModel = new KitchenModel();
        BeanUtils.copyProperties(kitchenDto, kitchenModel);
        kitchenModel.setId(kitchenOpt.get().getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(kitchenService.save(kitchenModel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable UUID id){
        try {
            Optional<KitchenModel> kitchenOpt = kitchenService.findById(id);
            if (kitchenOpt.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Kitchen Not Found");
            }
            kitchenService.delete(kitchenOpt.get());
            return ResponseEntity.status(HttpStatus.OK).body("Kitchen deleted successfully");
        }catch (DataIntegrityViolationException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("There is a restaurant attached to this type of Kitchen.");
        }
    }
}
