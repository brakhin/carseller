package ru.bgbrakhi.carseller.repository;

import org.springframework.data.repository.CrudRepository;
import ru.bgbrakhi.carseller.models.City;

public interface ICityRepository extends CrudRepository<City, Integer> {
    City findByName(String name);
}


