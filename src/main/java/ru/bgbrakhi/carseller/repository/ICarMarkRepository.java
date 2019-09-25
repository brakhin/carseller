package ru.bgbrakhi.carseller.repository;

import org.springframework.data.repository.CrudRepository;
import ru.bgbrakhi.carseller.models.CarMark;

public interface ICarMarkRepository extends CrudRepository<CarMark, Integer> {
    CarMark findByName(String name);
}
