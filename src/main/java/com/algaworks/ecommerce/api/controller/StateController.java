package com.algaworks.ecommerce.api.controller;

import com.algaworks.ecommerce.domain.model.State;
import com.algaworks.ecommerce.domain.service.StateService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/states")
public class StateController {

    @Autowired
    private StateService stateService;

    @GetMapping
    public List<State> toList() {
        return stateService.all() ;
    }

    @GetMapping("{id}")
    public ResponseEntity<State> toSearch(@PathVariable Long id) {
        State state = stateService.byId(id);

        if (state != null) {
            return ResponseEntity.ok(state);
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<State> toAdd(@RequestBody State state) {
        if (state != null) {
            state = stateService.toSave(state);
            return ResponseEntity.ok(state);
        }

        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> toChange(@PathVariable Long id, @RequestBody State state) {
        State stateDB = stateService.byId(id);

        if (stateDB != null ) {
            BeanUtils.copyProperties(state,stateDB,"id");
            state = stateService.toChange(stateDB);
            return ResponseEntity.ok(state);
        }

        return ResponseEntity.notFound().build();
    }
}
