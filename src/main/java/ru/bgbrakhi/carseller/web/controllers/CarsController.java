package ru.bgbrakhi.carseller.web.controllers;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import ru.bgbrakhi.carseller.UserFilter;
import ru.bgbrakhi.carseller.models.Car;
import ru.bgbrakhi.carseller.models.CarMark;
import ru.bgbrakhi.carseller.models.CarModel;
import ru.bgbrakhi.carseller.service.ICarModelService;
import ru.bgbrakhi.carseller.service.ICarService;
import ru.bgbrakhi.carseller.service.IStorageService;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;
import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class CarsController {
    public final static String COANTANT_FILTER_ALL_MARKS = "Все";

    @Autowired
    private IStorageService storageService;

    @Autowired
    private ServletContext servletContext;

    @Autowired
    private ICarService carService;

    @Autowired
    private ICarModelService carModelService;

    @GetMapping("/cars")
    public String showItems(ModelMap model, Principal principal) {
        loadData(null, model, principal);
        return "cars";
    }

    @PostMapping("/cars")
    public String setFilter(@ModelAttribute UserFilter filter, ModelMap model, Principal principal) {
        loadData(filter, model, principal);
        return "cars";
    }

    @GetMapping("/all_images/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> gatCarImage(@PathVariable String filename) {
        Resource file = storageService.loadAsResource(filename);
        if (file == null) {
            return null;
        } else {
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                    "attachment; filename=\"" + file.getFilename() + "\"").body(file);
        }
    }

    private void loadData(UserFilter filter, ModelMap model, Principal principal) {
        List<Car> cars = carService.findWithFilter(filter, true);
        List<CarModel> models = carModelService.findForType("");
        cars.forEach(car -> car.setFilename(
                car.getFilename().isEmpty()
                        ?
                        ""
                        :
                        MvcUriComponentsBuilder.fromMethodName(this.getClass(),
                                "gatCarImage", car.getFilename()).build().toString()
                )
        );
        model.addAttribute("login", principal == null ? "" : String.format(" [ %s ]", principal.getName()));
        model.addAttribute("cars", cars);
        model.addAttribute("marks", getMarks(models));
    }

    private List<CarMark> getMarks(List<CarModel> models) {
        List<CarMark> result = models.stream()
                .map(i -> i.getCarmark())
                .distinct()
                .collect(Collectors.toList());
        result.add(0, new CarMark(COANTANT_FILTER_ALL_MARKS));
        return result;
    }
}
