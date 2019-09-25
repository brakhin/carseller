package ru.bgbrakhi.carseller.web.controllers;

// https://www.baeldung.com/spring-file-upload

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import ru.bgbrakhi.carseller.models.*;
import ru.bgbrakhi.carseller.service.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.util.List;

@Controller
public class MyNotesController {

    @Autowired
    private ICarService carService;

    @Autowired
    private ICityService cityService;

    @Autowired
    private ICarTypeService carTypeService;

    @Autowired
    private ICarBodyService carBodyService;

    @Autowired
    private ICarMarkService carMarkService;

    @Autowired
    private ICarModelService carModelService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IStorageService storageService;

    @RequestMapping(value = "/mynotes", method = RequestMethod.GET)
    public String showItems(Principal principal, ModelMap model) {
        loadData(principal, model);
        return "mynotes";
    }

    @RequestMapping(value = "/mynotes", method = RequestMethod.POST)
    public String addData(@RequestParam  String edCity,
                          @RequestParam  String edType,
                          @RequestParam  String edMark,
                          @RequestParam  String edModel,
                          @RequestParam  String edBody,
                          @RequestParam  String edYear,
                          @RequestParam  String edPrice,
                          @RequestParam MultipartFile edFile,
                          HttpSession session,
                          Principal principal,
                          ModelMap model) throws Exception{

        String filename = storageService.store(edFile);

        Car car = new Car();

        CarType carType = carTypeService.getByName(edType);
        if (carType == null) {
            carType = new CarType();
            carType.setName(edType);
        }

        CarMark carMark = carMarkService.getByName(edMark);
        if (carMark == null) {
            carMark = new CarMark();
            carMark.setName(edMark);
        }

        City city = cityService.getByName(edCity);
        if (city == null) {
            city = new City();
            city.setName(edCity);
        }

        CarBody carBody = carBodyService.getByName(edBody);
        if (carBody == null) {
            carBody = new CarBody();
            carBody.setName(edBody);
        }

        CarModel carModel = carModelService.findByCartypeAndCarmarkAndName(carType, carMark, edModel);
        if (carModel == null) {
            carModel = new CarModel();
            carModel.setCartype(carType);
            carModel.setCarmark(carMark);
            carModel.setName(edModel);
        }

        User user = userService.findByLogin(principal.getName());

        car.setUser(user);
        car.setCity(city);
        car.setCarmodel(carModel);
        car.setCarbody(carBody);
        car.setYear(Integer.parseInt(edYear));
        car.setPrice(Integer.parseInt(edPrice));
        car.setFilename(filename);
        car.setInactive(false);

        Car result = carService.save(car);

        loadData(principal, model);
        return "mynotes";
    }

    @GetMapping("/note_images/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> gatNotesCarImage(@PathVariable String filename) {
        Resource file = storageService.loadAsResource(filename);
        if (file == null) {
            return null;
        } else {
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                    "attachment; filename=\"" + file.getFilename() + "\"").body(file);
        }
    }

    private void loadData(Principal principal, ModelMap model) {
        List<Car> cars = carService.findForUser(principal.getName());
        List<City> cities = cityService.getAll();
        List<CarType> carTypes = carTypeService.getAll();
        cars.forEach(car -> car.setFilename(
                car.getFilename().isEmpty()
                        ?
                        ""
                        :
                        MvcUriComponentsBuilder.fromMethodName(this.getClass(),
                                "gatNotesCarImage", car.getFilename()).build().toString()
                )
        );

        model.addAttribute("login", principal == null ? "" : String.format(" [ %s ]", principal.getName()));
        model.addAttribute("cars", cars);
        model.addAttribute("cities", cities);
        model.addAttribute("types", carTypes);
    }
}
