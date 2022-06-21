package com.algaworks.ecommerce.domain.service;

import com.algaworks.ecommerce.domain.model.Restaurant;
import com.algaworks.ecommerce.domain.repository.RestaurantRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantSignupService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    public List<Restaurant> all() {
        return restaurantRepository.all();
    }

    public Restaurant byId(Long id) {
        try {
            return restaurantRepository.byId(id);
        } catch (NullPointerException e) {
            throw new NullPointerException("This id do not correspond to any restaurant in DB");
        }
    }

    public Restaurant toSave(Restaurant restaurant) {
        return restaurantRepository.toAdd(restaurant);
    }

    public Restaurant toChange(Long id, Restaurant restaurant) {

        Restaurant restaurantAlreadyHere = restaurantRepository.byId(id);

        BeanUtils.copyProperties(restaurant, restaurantAlreadyHere,"id","name");

        return restaurantRepository.toAdd(restaurantAlreadyHere);
    }
}
