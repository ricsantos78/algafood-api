package com.algaworks.algafoodapi.controllers;

import com.algaworks.algafoodapi.domain.model.StateModel;
import com.algaworks.algafoodapi.services.StateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/states")
public class StateController {

    private final StateService stateService;

    @GetMapping
    public ResponseEntity<List<StateModel>> findAll() {
        List<StateModel> states = stateService.findAll();
        return new ResponseEntity<>(states, HttpStatus.OK);
    }
}
