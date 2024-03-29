package com.algaworks.ecommerce.api.controller;


import com.algaworks.ecommerce.domain.exception.EntityInUseException;
import com.algaworks.ecommerce.domain.model.City;
import com.algaworks.ecommerce.domain.service.CityService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cities")
public class CityController {

    @Autowired
    private CityService cityService;

    @GetMapping
    public List<City> toList() {
        return cityService.all() ;
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<City>> toSearch(@PathVariable Long id) {
        Optional<City> cityDB = cityService.byId(id);

        if (cityDB.isPresent()) {
            return ResponseEntity.ok(cityDB);
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<City> toAdd(@RequestBody City city) {
        if (city != null) {
            city = cityService.toSave(city);
            return ResponseEntity.ok(city);
        }

        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> toChange(@PathVariable Long id, @RequestBody City city) {
        Long stateId = city.getState().getId();
        Optional<City> cityDB = cityService.byId(id);

        try {
            if (cityDB.isPresent()) {
                BeanUtils.copyProperties(city, cityDB, "id");
                city = cityService.toChange(cityDB.get());
                return ResponseEntity.ok(city);
            }
            return ResponseEntity.notFound().build();
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest()
                    .body(String.format("There is no such state with this id: %d", stateId));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<City> toDelete(@PathVariable Long id) {
        try {
            cityService.toDelete(id);
            return ResponseEntity.noContent().build();
        } catch (EntityInUseException e) {

            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } catch (EntityNotFoundException e){

            return ResponseEntity.notFound().build();
        }
    }
}
