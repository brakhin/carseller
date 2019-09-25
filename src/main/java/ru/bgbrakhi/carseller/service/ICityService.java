package ru.bgbrakhi.carseller.service;

import ru.bgbrakhi.carseller.models.City;

import java.util.List;

public interface ICityService {
    List<City> getAll();
    City getByName(String name);
}
