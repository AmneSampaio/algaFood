package com.algaworks.ecommerce.infrastructure.repository;

import com.algaworks.ecommerce.domain.model.PaymentType;
import com.algaworks.ecommerce.domain.repository.PaymentTypeRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class PaymentTypeRepositoryImpl implements PaymentTypeRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<PaymentType> all() {
        return manager.createQuery("from PaymentType", PaymentType.class)
                .getResultList();
    }

    @Override
    @Transactional
    public PaymentType toAdd(PaymentType paymentType) {
        return manager.merge(paymentType);
    }

    @Override
    public PaymentType byId(Long id) {
        return manager.find(PaymentType.class, id);
    }

    @Override
    @Transactional
    public void toDelete(PaymentType paymentType) {
        paymentType = byId(paymentType.getId());
        manager.remove(paymentType);
    }
}
