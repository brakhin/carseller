package ru.bgbrakhi.carseller.repository;

import org.springframework.data.repository.CrudRepository;
import ru.bgbrakhi.carseller.models.CarType;

public interface ICarTypeRepository extends CrudRepository<CarType, Integer> {
    CarType findByName(String name);
}


