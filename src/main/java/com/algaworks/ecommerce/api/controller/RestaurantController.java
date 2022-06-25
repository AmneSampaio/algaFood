package com.algaworks.ecommerce.api.controller;

import com.algaworks.ecommerce.domain.model.Restaurant;
import com.algaworks.ecommerce.domain.service.RestaurantSignupService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantSignupService restaurantSignupService;

    @GetMapping
    public List<Restaurant> toList() {
        return restaurantSignupService.all();
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
    public ResponseEntity<?> toAdd(@RequestBody Restaurant restaurant) {
        Long kitchenId = restaurant.getKitchen().getId();
        try {
            restaurant = restaurantSignupService.toSave(restaurant);

            return ResponseEntity.status(HttpStatus.CREATED).body(restaurant);
        } catch (EntityNotFoundException e) {

            return ResponseEntity.badRequest()
                    .body(String.format("There is no such kitchen with this id: %d", kitchenId));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> toChange(@PathVariable Long id, @RequestBody Restaurant restaurant) {
        Long kitchenId = restaurant.getKitchen().getId();

        try {
            Restaurant restaurantDB = restaurantSignupService.byId(id);
            if (restaurantDB != null) {
                BeanUtils.copyProperties(restaurant, restaurantDB,"id");
                restaurant = restaurantSignupService.toChange(restaurantDB);
                return ResponseEntity.ok(restaurant);
            }
            return ResponseEntity.notFound().build();
        } catch (EntityNotFoundException| DataIntegrityViolationException e) {

            return ResponseEntity.badRequest()
                    .body(String.format("There is no such kitchen with this id: %d", kitchenId));
        }
    }
}
