package com.algaworks.ecommerce.api.controller;

import com.algaworks.ecommerce.domain.model.State;
import com.algaworks.ecommerce.domain.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/state")
public class StateController {

    @Autowired
    private StateRepository stateRepository;

    @GetMapping
    public List<State> toList() {
        return stateRepository.all() ;
    }
}
