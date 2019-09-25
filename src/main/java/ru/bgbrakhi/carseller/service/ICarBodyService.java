package ru.bgbrakhi.carseller.service;

import ru.bgbrakhi.carseller.models.CarBody;

import java.util.List;

public interface ICarBodyService {
    List<CarBody> getCarBodyByType(String cartype);
    CarBody getByName(String name);
    CarBody save(CarBody car);

}
