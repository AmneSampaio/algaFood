package com.algaworks.ecommerce.infrastructure.repository;

import com.algaworks.ecommerce.domain.model.Kitchen;
import com.algaworks.ecommerce.domain.repository.KitchenRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class KitchenRepositoryImpl implements KitchenRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Kitchen> all() {
        return manager.createQuery("from Kitchen", Kitchen.class)
                .getResultList();
    }

    @Transactional
    @Override
    public Kitchen toAdd(Kitchen kitchen) {
        return manager.merge(kitchen);
    }

    @Override
    public Kitchen byId(Long id) {
        return manager.find(Kitchen.class, id);
    }

    @Override
    @Transactional
    public void toDelete(Long id) {
        Kitchen kitchen = byId(id);

        if (kitchen == null) {
            throw new EmptyResultDataAccessException(1);
        }

        manager.remove(kitchen);
    }
}
