package com.algaworks.ecommerce.infrastructure.repository;

import com.algaworks.ecommerce.domain.model.City;
import com.algaworks.ecommerce.domain.repository.CityRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class CityRepositoryImpl implements CityRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<City> all() {
        return manager.createQuery("from City", City.class)
                .getResultList();
    }
    @Override
    @Transactional
    public City toAdd(City city) {
        return manager.merge(city);
    }

    @Override
    public City byId(Long id) {
        return manager.find(City.class, id);
    }

    @Override
    @Transactional
    public void toDelete(Long id) {
        City city = byId(id);

        if (city == null) {
            throw new EmptyResultDataAccessException(1);
        }

        manager.remove(city);
    }
}
