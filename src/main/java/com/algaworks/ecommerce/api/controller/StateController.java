package com.algaworks.ecommerce.api.controller;

import com.algaworks.ecommerce.domain.exception.EntityInUseException;
import com.algaworks.ecommerce.domain.model.State;
import com.algaworks.ecommerce.domain.service.StateService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/states")
public class StateController {

    @Autowired
    private StateService stateService;

    @GetMapping
    public List<State> toList() {
        return stateService.all();
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<State>> toSearch(@PathVariable Long id) {
        Optional<State> stateDB = stateService.byId(id);

        if (stateDB.isPresent()) {
            return ResponseEntity.ok(stateDB);
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
    public ResponseEntity<?> toChange(@PathVariable Long id, @RequestBody Optional<State> state) {
        Optional<State> stateDB = stateService.byId(id);

        if (stateDB.isPresent()) {
            BeanUtils.copyProperties(state, stateDB, "id");
            state = Optional.ofNullable(stateService.toChange(stateDB.get()));
            return ResponseEntity.ok(state);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<State> toDelete(@PathVariable Long id) {
        try {
            stateService.toDelete(id);
            return ResponseEntity.noContent().build();
        } catch (EntityInUseException e) {

            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } catch (EntityNotFoundException e) {

            return ResponseEntity.notFound().build();
        }
    }
}
