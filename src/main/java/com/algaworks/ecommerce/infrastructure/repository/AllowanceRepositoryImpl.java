package com.algaworks.ecommerce.infrastructure.repository;

import com.algaworks.ecommerce.domain.model.Allowance;
import com.algaworks.ecommerce.domain.model.Kitchen;
import com.algaworks.ecommerce.domain.repository.AllowanceRepository;
import com.algaworks.ecommerce.domain.repository.KitchenRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class AllowanceRepositoryImpl implements AllowanceRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Allowance> all() {
        return manager.createQuery("from Allowance", Allowance.class)
                .getResultList();
    }
    @Override
    @Transactional
    public Allowance toAdd(Allowance allowance) {
        return manager.merge(allowance);
    }

    @Override
    public Allowance byId(Long id) {
        return manager.find(Allowance.class, id);
    }

    @Override
    @Transactional
    public void toDelete(Allowance allowance) {
        allowance = byId(allowance.getId());
        manager.remove(allowance);
    }
}
