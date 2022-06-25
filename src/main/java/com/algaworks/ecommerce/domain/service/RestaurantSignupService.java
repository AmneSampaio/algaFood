package com.algaworks.ecommerce.domain.service;

import com.algaworks.ecommerce.domain.exception.EntityInUseException;
import com.algaworks.ecommerce.domain.model.Restaurant;
import com.algaworks.ecommerce.domain.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
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

    public Restaurant toChange(Restaurant restaurant) {
        return restaurantRepository.toAdd(restaurant);
    }

    public void toDelete(Long id) {

        try {
            restaurantRepository.toDelete(id);
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
