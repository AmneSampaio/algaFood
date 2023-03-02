package com.algaworks.ecommerce.domain.service;

import com.algaworks.ecommerce.domain.exception.EntityInUseException;
import com.algaworks.ecommerce.domain.model.Kitchen;
import com.algaworks.ecommerce.domain.repository.KitchenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class KitchenSignupService {

    @Autowired
    private KitchenRepository kitchenRepository;

    public List<Kitchen> all() {
        return kitchenRepository.findAll();
    }

    public Kitchen toSearchByName(String name) {
        return kitchenRepository.findByName(name);
    }



    public Kitchen toSave(Kitchen kitchen) {
        return kitchenRepository.save(kitchen);
    }

    public void toDelete(Long id) {
        try {
            kitchenRepository.deleteById(id);
        } catch (EntityNotFoundException| EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(String.format("Not found kitchen related to the id %d.", id));
        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(
                    String.format("Kitchen of id %d can't be removed, " +
                            "it has children or dependency with another " +
                            "entity.", id));
        }

    }


}
