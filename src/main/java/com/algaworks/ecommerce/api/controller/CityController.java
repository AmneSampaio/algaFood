package com.algaworks.ecommerce.api.controller;


import com.algaworks.ecommerce.domain.model.City;
import com.algaworks.ecommerce.domain.service.CityService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<City> toSearch(@PathVariable Long id) {
        City city = cityService.byId(id);

        if (city != null) {
            return ResponseEntity.ok(city);
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

        try {
            City cityDB = cityService.byId(id);
            if (cityDB != null) {
                BeanUtils.copyProperties(city, cityDB, "id");
                city = cityService.toChange(cityDB);
                return ResponseEntity.ok(city);
            }
            return ResponseEntity.notFound().build();
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest()
                    .body(String.format("There is no such state with this id: %d", stateId));
        }
    }


}
