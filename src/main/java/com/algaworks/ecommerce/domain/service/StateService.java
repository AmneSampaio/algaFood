package com.algaworks.ecommerce.domain.service;

import com.algaworks.ecommerce.domain.model.State;
import com.algaworks.ecommerce.domain.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StateService {

    @Autowired
    private StateRepository stateRepository;


    public List<State> all() {
        return stateRepository.all();
    }

    public State byId(Long id) {
        return stateRepository.byId(id);
    }

    public State toSave(State state) {
        return stateRepository.toAdd(state);
    }

    public State toChange(State state) { return stateRepository.toAdd(state);
    }
}
