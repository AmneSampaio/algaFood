package com.algaworks.ecommerce.domain.repository;

import com.algaworks.ecommerce.domain.model.Allowance;

import java.util.List;

public interface AllowanceRepository {

    List<Allowance> all();
    Allowance byId(Long id);
    Allowance toAdd(Allowance allowance);
    void toDelete(Allowance allowance);
}
