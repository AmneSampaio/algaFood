package com.algaworks.ecommerce.domain.repository;

import com.algaworks.ecommerce.domain.model.Kitchen;
import com.algaworks.ecommerce.domain.model.PaymentType;

import java.util.List;

public interface PaymentTypeRepository {

    List<PaymentType> all();
    PaymentType byId(Long id);
    PaymentType toAdd(PaymentType paymentType);
    void toDelete(PaymentType paymentType);
}
