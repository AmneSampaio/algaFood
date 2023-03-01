package com.algaworks.ecommerce.domain.service;

import com.algaworks.ecommerce.domain.exception.EntityInUseException;
import com.algaworks.ecommerce.domain.model.City;
import com.algaworks.ecommerce.domain.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    public List<City> all() {
        return cityRepository.findAll();
    }

    public Optional<City> byId(Long id) {
        return cityRepository.findById(id);
    }

    public City toSave(City city) {
        return cityRepository.save(city);
    }

    public City toChange(City city) {
        return cityRepository.save(city);
    }

    public void toDelete(Long id) {
        try {
            cityRepository.deleteById(id);
        } catch (EntityNotFoundException | EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(String.format("Not found city related to the id %d.", id));
        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(
                    String.format("City of id %d can't be removed, " +
                            "it has children or dependency with another " +
                            "entity.", id));
        }
    }
}
