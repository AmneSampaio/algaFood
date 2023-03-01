package com.algaworks.ecommerce.domain.repository;

import com.algaworks.ecommerce.domain.model.PaymentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentTypeRepository extends JpaRepository<PaymentType, Long> {

}
