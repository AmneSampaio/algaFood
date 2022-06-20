package com.algaworks.ecommerce.domain.service;

import com.algaworks.ecommerce.domain.exception.EntityInUseException;
import com.algaworks.ecommerce.domain.model.Kitchen;
import com.algaworks.ecommerce.domain.repository.KitchenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class KitchenSignupService {

    @Autowired
    private KitchenRepository kitchenRepository;



    public Kitchen toSave(Kitchen kitchen) {
        return kitchenRepository.toAdd(kitchen);
    }

    public void toDelete(Long id) {
        try {
            kitchenRepository.toDelete(id);
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException(String.format("Not found kitchen related to the id %d.", id));
        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(
                    String.format("Kitchen of id %d can't be removed, " +
                            "it has children or dependency with another " +
                            "entity.", id));
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(String.format("Not found kitchen related to the id %d.", id));
        }

    }

}
