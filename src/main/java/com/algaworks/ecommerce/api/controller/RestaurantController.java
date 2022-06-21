package com.algaworks.ecommerce.api.controller;

import com.algaworks.ecommerce.domain.model.Restaurant;
import com.algaworks.ecommerce.domain.service.RestaurantSignupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantSignupService restaurantSignupService ;

    @GetMapping
    public List<Restaurant> toList() {
        return restaurantSignupService.all() ;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> toSearch(@PathVariable Long id) {
        Restaurant restaurant = restaurantSignupService.byId(id);

        if (restaurant == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(restaurant);
    }

    @PostMapping
    public ResponseEntity<Restaurant> toAdd(@RequestBody Restaurant restaurant) {
        return ResponseEntity.status(HttpStatus.CREATED).body(restaurantSignupService.toSave(restaurant));
    }
}
