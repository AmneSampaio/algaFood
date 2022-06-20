package com.algaworks.ecommerce.domain.repository;

import com.algaworks.ecommerce.domain.model.Kitchen;
import com.algaworks.ecommerce.domain.model.Restaurant;

import java.util.List;

public interface RestaurantRepository {

    List<Restaurant> all();
    Restaurant byId(Long id);
    Restaurant toAdd(Restaurant restaurant);
    void toDelete(Restaurant restaurant);
}
