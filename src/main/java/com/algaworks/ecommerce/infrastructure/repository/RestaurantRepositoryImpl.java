package com.algaworks.ecommerce.infrastructure.repository;

import com.algaworks.ecommerce.domain.model.Restaurant;
import com.algaworks.ecommerce.domain.repository.KitchenRepository;
import com.algaworks.ecommerce.domain.repository.RestaurantRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class RestaurantRepositoryImpl implements RestaurantRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Restaurant> all() {
        return manager.createQuery("from Restaurant", Restaurant.class)
                .getResultList();
    }
    @Override
    @Transactional
    public Restaurant toAdd(Restaurant restaurant) {
        return manager.merge(restaurant);
    }

    @Override
    public Restaurant byId(Long id) {
        return manager.find(Restaurant.class, id);
    }

    @Override
    @Transactional
    public void toDelete(Restaurant restaurant) {
        restaurant = byId(restaurant.getId());
        manager.remove(restaurant);
    }
}
