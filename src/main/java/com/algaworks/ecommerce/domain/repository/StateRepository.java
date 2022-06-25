package com.algaworks.ecommerce.domain.repository;

import com.algaworks.ecommerce.domain.model.State;

import java.util.List;

public interface StateRepository {

    List<State> all();
    State byId(Long id);
    State toAdd(State state);
    void toDelete(Long id);
}
