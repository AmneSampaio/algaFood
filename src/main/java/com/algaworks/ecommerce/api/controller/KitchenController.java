package com.algaworks.ecommerce.api.controller;

import com.algaworks.ecommerce.domain.exception.EntityInUseException;
import com.algaworks.ecommerce.domain.model.Kitchen;
import com.algaworks.ecommerce.domain.repository.KitchenRepository;
import com.algaworks.ecommerce.domain.service.KitchenSignupService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("kitchens")
public class KitchenController {

    @Autowired
    private KitchenRepository kitchenRepository;

    @Autowired
    private KitchenSignupService kitchenSignupService;

    @GetMapping
    public List<Kitchen> toList() {
        return kitchenSignupService.all() ;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Kitchen> toSearch(@PathVariable Long id) {
        Kitchen kitchen = kitchenRepository.byId(id);

        if (kitchen == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(kitchen);
    }

    @PostMapping
    public ResponseEntity<Kitchen> toAdd(@RequestBody Kitchen kitchen) {
        return ResponseEntity.status(HttpStatus.CREATED).body(kitchenSignupService.toSave(kitchen));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Kitchen> toChange(@PathVariable Long id, @RequestBody Kitchen kitchen) {
        Kitchen kitchenDB = kitchenRepository.byId(id);

        if (kitchenDB != null) {
            BeanUtils.copyProperties(kitchen,kitchenDB, "id");

            kitchenDB = kitchenRepository.toAdd(kitchenDB);
            return ResponseEntity.ok(kitchenDB);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Kitchen> toDelete(@PathVariable Long id){
        try {
            kitchenSignupService.toDelete(id);
            return ResponseEntity.noContent().build();
        } catch (EntityInUseException e) {

            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } catch (EntityNotFoundException e){

            return ResponseEntity.notFound().build();
        }
    }
}
