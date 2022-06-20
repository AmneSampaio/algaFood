package com.algaworks.ecommerce.domain.repository;

import com.algaworks.ecommerce.domain.model.Kitchen;

import java.util.List;

public interface KitchenRepository {

    List<Kitchen> all();
    Kitchen byId(Long id);
    Kitchen toAdd(Kitchen kitchen);
    void toDelete(Long id);
}
