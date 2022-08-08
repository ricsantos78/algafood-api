package com.algaworks.algafoodapi.controllers;

import com.algaworks.algafoodapi.domain.dto.CityDto;
import com.algaworks.algafoodapi.domain.model.CityModel;
import com.algaworks.algafoodapi.services.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/citys")
public class CityController {

    private final CityService cityService;

    @GetMapping
    public ResponseEntity<List<CityModel>> findAll(){
        return ResponseEntity.ok(cityService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable UUID id){
        var cityFindById = cityService.findById(id);
        if (cityFindById.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("City Not Found!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(cityFindById.get());
    }

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody CityDto cityDto){
        var cityFindByName = cityService.findCityModelByName(cityDto.getName());
        if (cityFindByName.isPresent()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("City Already Exists!");
        }
        if(!cityService.existsCityModelByStateId(cityDto.getState().getId())){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("State Not Found!");
        }
        CityModel cityModel = new CityModel();
        BeanUtils.copyProperties(cityDto,cityModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(cityService.save(cityModel));
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<Object> update(@PathVariable UUID id
//    , @RequestBody CityDto cityDto){
//
//    }
}
