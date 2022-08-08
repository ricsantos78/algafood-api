package com.algaworks.algafoodapi.controllers;

import com.algaworks.algafoodapi.domain.dto.StateDto;
import com.algaworks.algafoodapi.domain.model.StateModel;
import com.algaworks.algafoodapi.services.StateService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/states")
public class StateController {

    private final StateService stateService;

    @GetMapping
    public ResponseEntity<List<StateModel>> findAll() {
        return ResponseEntity.ok(stateService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable UUID id){
        var stateFindById = stateService.findById(id);
        return stateFindById.<ResponseEntity<Object>>map(states -> ResponseEntity.status(HttpStatus.OK).body(states))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("State Not Found!"));
    }

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody StateDto stateDto){

        if (Boolean.TRUE.equals(stateService.existsByName(stateDto.getName()))){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("State name already exists!");
        }
        StateModel stateModel = new StateModel();
        BeanUtils.copyProperties(stateDto,stateModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(stateService.save(stateModel));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable UUID id
    , @RequestBody StateDto stateDto){
        var stateFindById = stateService.findById(id);
        if(stateFindById.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("State Not Found!");
        }
        if(Boolean.TRUE.equals(stateService.existsByName(stateDto.getName()))){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("State name already exists!");
        }
        StateModel stateModel = new StateModel();
        BeanUtils.copyProperties(stateDto, stateModel);
        stateModel.setId(stateFindById.get().getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(stateService.save(stateModel));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable UUID id){
        var stateFindById = stateService.findById(id);
        try {
            if (stateFindById.isEmpty()) {
                 return ResponseEntity.status(HttpStatus.NOT_FOUND).body("State Not Found!");
            }
            stateService.delete(stateFindById.get());
             return ResponseEntity.status(HttpStatus.OK).body("State deleted successfully");
        }catch (DataIntegrityViolationException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("There is a States attached to this type of City.");
        }
    }
}
