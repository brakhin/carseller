package ru.bgbrakhi.carseller.service;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import ru.bgbrakhi.carseller.models.City;
import ru.bgbrakhi.carseller.repository.ICityRepository;

import java.util.List;

@Service
@Repository
public class CityServiceImpl implements ICityService {

    @Autowired
    private ICityRepository cityRepository;

    @Override
    public List<City> getAll() {
        return Lists.newArrayList(cityRepository.findAll());

    }

    @Override
    public City getByName(String name) {
        return cityRepository.findByName(name);
    }
}
