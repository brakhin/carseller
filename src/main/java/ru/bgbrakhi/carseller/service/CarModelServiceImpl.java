package ru.bgbrakhi.carseller.service;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import ru.bgbrakhi.carseller.models.CarMark;
import ru.bgbrakhi.carseller.models.CarModel;
import ru.bgbrakhi.carseller.models.CarType;
import ru.bgbrakhi.carseller.repository.ICarModelRepository;

import java.util.List;

@Service
@Repository
public class CarModelServiceImpl implements ICarModelService {

    @Autowired
    private ICarModelRepository carModelRepository;

    @Override
    public List<CarModel> findForType(String cartype) {
        return cartype.isEmpty()
                ?
                Lists.newArrayList(carModelRepository.findAll())
                :
                carModelRepository.findByCartype_Name(cartype);
    }

    @Override
    public CarModel findByCartypeAndCarmarkAndName(CarType carType, CarMark carMark, String name) {
        return carModelRepository.findByCartypeAndCarmarkAndName(carType, carMark, name);
    }
}
