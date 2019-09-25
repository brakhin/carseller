package ru.bgbrakhi.carseller.service;

import ru.bgbrakhi.carseller.models.CarType;

import java.util.List;

public interface ICarTypeService {
    List<CarType> getAll();
    CarType getByName(String name);
}
