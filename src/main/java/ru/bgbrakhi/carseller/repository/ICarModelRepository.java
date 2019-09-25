package ru.bgbrakhi.carseller.repository;

import org.springframework.data.repository.CrudRepository;
import ru.bgbrakhi.carseller.models.CarMark;
import ru.bgbrakhi.carseller.models.CarModel;
import ru.bgbrakhi.carseller.models.CarType;

import java.util.List;

public interface ICarModelRepository extends CrudRepository<CarModel, Integer> {
    List<CarModel> findByCartype_Name(String carType);
    CarModel findByCartypeAndCarmarkAndName(CarType carType, CarMark carMark, String name);
}
