package ru.bgbrakhi.carseller.service;

import ru.bgbrakhi.carseller.models.CarMark;

import java.util.List;

public interface ICarMarkService {
    List<CarMark> findForType(String cartype);
    CarMark getByName(String name);
}
