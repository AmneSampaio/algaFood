package com.algaworks.ecommerce.domain.service;

import com.algaworks.ecommerce.domain.exception.EntityInUseException;
import com.algaworks.ecommerce.domain.model.State;
import com.algaworks.ecommerce.domain.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class StateService {

    @Autowired
    private StateRepository stateRepository;


    public List<State> all() {
        return stateRepository.findAll();
    }

    public Optional<State> byId(Long id) {
        return stateRepository.findById(id);
    }

    public State toSave(State state) {
        return stateRepository.save(state);
    }

    public State toChange(State state) {
        return stateRepository.save(state);
    }

    public void toDelete(Long id) {

        try {
            stateRepository.deleteById(id);
        } catch (EntityNotFoundException | EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(String.format("Not found state related to the id %d.", id));
        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(
                    String.format("State of id %d can't be removed, " +
                            "it has children or dependency with another " +
                            "entity.", id));
        }
    }
}
