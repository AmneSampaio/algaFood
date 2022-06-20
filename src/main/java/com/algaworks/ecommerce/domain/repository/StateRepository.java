package com.algaworks.ecommerce.domain.repository;

import com.algaworks.ecommerce.domain.model.Kitchen;
import com.algaworks.ecommerce.domain.model.State;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface StateRepository {

    List<State> all();
    State byId(Long id);
    State toAdd(State state);
    void toDelete(State state);
}
