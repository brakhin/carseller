package ru.bgbrakhi.carseller.service;

import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bgbrakhi.carseller.UserFilter;
import ru.bgbrakhi.carseller.models.Car;
import ru.bgbrakhi.carseller.repository.ICarRepository;

import java.util.List;

import static ru.bgbrakhi.carseller.web.controllers.CarsController.COANTANT_FILTER_ALL_MARKS;

@Service
@Repository
@Transactional
public class CarServiceImpl implements ICarService {

    @Autowired
    private ICarRepository carRepository;

    @Override
    public List<Car> findWithFilter(UserFilter filter, Boolean onlyActive) {
        return carRepository.findWithFilter(!onlyActive,
                filter != null
                        && !COANTANT_FILTER_ALL_MARKS.equals(filter.getMark())
                        && !filter.getMark().isEmpty(),
                filter == null ? "" : filter.getMark(),
                filter != null && filter.getToday(),
                filter != null && filter.getWithphoto());
    }

    @Override
    public List<Car> findForUser(String login) {
        return carRepository.findForUser(login);
    }

    @Override
    public void swapInactive(Long id, String login) {
        carRepository.swapInactive(id, login);
    }

    @Override
    public Car save(Car car) {
        return carRepository.save(car);
    }
}
