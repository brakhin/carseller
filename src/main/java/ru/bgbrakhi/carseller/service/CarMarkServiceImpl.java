package ru.bgbrakhi.carseller.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import ru.bgbrakhi.carseller.models.CarMark;
import ru.bgbrakhi.carseller.models.CarModel;
import ru.bgbrakhi.carseller.repository.ICarMarkRepository;
import ru.bgbrakhi.carseller.repository.ICarModelRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Repository
public class CarMarkServiceImpl implements ICarMarkService {

    @Autowired
    private ICarModelRepository carModelRepository;

    @Autowired
    private ICarMarkRepository carMarkRepository;


    @Override
    public List<CarMark> findForType(String cartype) {
        List<CarModel> items = carModelRepository.findByCartype_Name(cartype);
        return items.stream()
                .map(i -> i.getCarmark())
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public CarMark getByName(String name) {
        return carMarkRepository.findByName(name);
    }
}
