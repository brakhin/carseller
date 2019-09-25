package ru.bgbrakhi.carseller.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import ru.bgbrakhi.carseller.models.CarBody;
import ru.bgbrakhi.carseller.repository.ICarBodyRepository;

import java.util.List;

@Service
@Repository
public class CarBodyServiceImpl implements ICarBodyService {

    @Autowired
    ICarBodyRepository carBodyRepository;

    @Override
    public List<CarBody> getCarBodyByType(String cartype) {
        return carBodyRepository.getCarBodyByType(cartype);
    }

    @Override
    public CarBody getByName(String name) {
        return carBodyRepository.findByName(name);
    }

    @Override
    public CarBody save(CarBody car) {
        return carBodyRepository.save(car);
    }
}
