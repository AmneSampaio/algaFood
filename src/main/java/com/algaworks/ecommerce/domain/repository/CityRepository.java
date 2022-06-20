package com.algaworks.ecommerce.domain.repository;

import com.algaworks.ecommerce.domain.model.City;
import com.algaworks.ecommerce.domain.model.Kitchen;

import java.util.List;

public interface CityRepository {

    List<City> all();
    City byId(Long id);
    City toAdd(City city);
    void toDelete(City city);
}
