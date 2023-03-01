package com.algaworks.ecommerce.domain.repository;

import com.algaworks.ecommerce.domain.model.Allowance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AllowanceRepository extends JpaRepository<Allowance, Long> {

}
