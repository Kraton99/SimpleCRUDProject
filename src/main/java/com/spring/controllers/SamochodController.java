package com.spring.controllers;

import com.spring.entities.Car;
import com.spring.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class SamochodController {

    @Autowired
    CarService carService;

    @RequestMapping(value = "/cars", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Car> list(Model model) {
        return carService.listAllCars();
    }

    @RequestMapping(value = "/cars/{page}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Car> findAll(@PathVariable("page") Integer pageNr, @RequestParam("size") Optional<Integer> howManyOnPage){
        return carService.findAllCarsPaging(pageNr, howManyOnPage.orElse(1));
    }
    @RequestMapping(value = "/car/{vin}", method = RequestMethod.GET)
    public Iterable<Car> list(@PathVariable("vin") String vin) {
        return carService.findCarByVin(vin);
    }

    @RequestMapping(value = "/car/mileage", method = RequestMethod.GET)
    public ResponseEntity<Integer> list() {
        return new ResponseEntity<>(carService.averageMileage(),HttpStatus.OK);
    }

    @RequestMapping(value = "/car/{vin}", method = RequestMethod.DELETE)
    public Iterable<Car> delete(@PathVariable("vin") String vin) {
        carService.deleteCar(vin);
        return carService.listAllCars();
    }
    @RequestMapping(value = "/car", method = RequestMethod.POST)
    public ResponseEntity<Car> create(@RequestBody @Valid @NotNull Car car) {
        carService.saveCar(car);
        return ResponseEntity.ok().body(car);
    }
    @RequestMapping(value = "/car", method = RequestMethod.PUT)
    public ResponseEntity<Car> edit(@RequestBody @Valid @NotNull Car car) {
        if(carService.checkIfExist(car.getVin())) {
            carService.saveCar(car);
            return ResponseEntity.ok().body(car);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
