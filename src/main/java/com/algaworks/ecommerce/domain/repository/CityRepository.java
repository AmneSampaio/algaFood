package com.algaworks.ecommerce.domain.repository;

import com.algaworks.ecommerce.domain.model.City;

import java.util.List;

public interface CityRepository {

    List<City> all();
    City byId(Long id);
    City toAdd(City city);
    void toDelete(Long id);
}
