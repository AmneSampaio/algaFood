package com.algaworks.ecommerce.domain.service;

import com.algaworks.ecommerce.domain.exception.EntityInUseException;
import com.algaworks.ecommerce.domain.model.Kitchen;
import com.algaworks.ecommerce.domain.model.Restaurant;
import com.algaworks.ecommerce.domain.repository.KitchenRepository;
import com.algaworks.ecommerce.domain.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class RestaurantSignupService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private KitchenRepository kitchenRepository;

    public List<Restaurant> all() {
        return restaurantRepository.findAll();
    }

    public Restaurant byId(Long id) {

        Optional<Restaurant> restaurantDB = restaurantRepository.findById(id);
        try {
            return restaurantDB.get();
        } catch (NullPointerException e) {
            throw new NullPointerException("This id do not correspond to any restaurant in DB");
        }
    }

    public Restaurant toSave(Restaurant restaurant) {
        Long kitchenId = restaurant.getKitchen().getId();
        Kitchen kitchenDB = kitchenRepository.findById(kitchenId)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("There's no kitchen with code %d",kitchenId)));

        return restaurantRepository.save(restaurant);
    }

    public Restaurant toChange(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    public void toDelete(Long id) {

        try {
            restaurantRepository.deleteById(id);
        } catch (EntityNotFoundException | EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(String.format("Not found restaurant related to the id %d.", id));
        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(
                    String.format("Restaurant of id %d can't be removed, " +
                            "it has children or dependency with another " +
                            "entity.", id));
        }
    }
}
