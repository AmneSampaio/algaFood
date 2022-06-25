package com.algaworks.ecommerce.domain.service;

import com.algaworks.ecommerce.domain.model.City;
import com.algaworks.ecommerce.domain.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    public List<City> all() {
        return cityRepository.all();
    }

    public City byId(Long id) {
        return cityRepository.byId(id);
    }

    public City toSave(City city) {
        return cityRepository.toAdd(city);
    }

    public City toChange(City city) {
        return cityRepository.toAdd(city);
    }
}
