package ru.bgbrakhi.carseller.service;

import ru.bgbrakhi.carseller.models.CarMark;
import ru.bgbrakhi.carseller.models.CarModel;
import ru.bgbrakhi.carseller.models.CarType;

import java.util.List;

public interface ICarModelService {
    List<CarModel> findForType(String carType);
    CarModel findByCartypeAndCarmarkAndName(CarType carType, CarMark carMark, String name);
}
