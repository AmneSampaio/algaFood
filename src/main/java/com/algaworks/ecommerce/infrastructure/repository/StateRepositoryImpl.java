package com.algaworks.ecommerce.infrastructure.repository;

import com.algaworks.ecommerce.domain.model.Kitchen;
import com.algaworks.ecommerce.domain.model.State;
import com.algaworks.ecommerce.domain.repository.KitchenRepository;
import com.algaworks.ecommerce.domain.repository.StateRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class StateRepositoryImpl implements StateRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<State> all() {
        return manager.createQuery("from State", State.class)
                .getResultList();
    }
    @Override
    @Transactional
    public State toAdd(State state) {
        return manager.merge(state);
    }

    @Override
    public State byId(Long id) {
        return manager.find(State.class, id);
    }

    @Override
    @Transactional
    public void toDelete(State state) {
        state = byId(state.getId());
        manager.remove(state);
    }
}
