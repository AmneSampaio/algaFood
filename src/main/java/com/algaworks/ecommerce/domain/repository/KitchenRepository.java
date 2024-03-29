package com.algaworks.ecommerce.domain.repository;

import com.algaworks.ecommerce.domain.model.Kitchen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KitchenRepository extends JpaRepository<Kitchen, Long> {
    Kitchen findByName(String name);
}
