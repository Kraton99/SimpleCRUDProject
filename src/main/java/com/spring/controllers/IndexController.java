package com.spring.controllers;


import com.spring.entities.*;
import com.spring.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Set;





@RestController
@RequestMapping("/")
public class IndexController {

    @Autowired
    DriverService driverService;
    @Autowired
    BrandService brandService;
    @Autowired
    ModelService modelService;
    @Autowired
    CarService carService;
    @Autowired
    EngineService engineService;


    @RequestMapping(value = "")
    String index() {
        return "index";
    }




    @RequestMapping(value = "generateModel", method = RequestMethod.POST, produces = MediaType.TEXT_PLAIN_VALUE)
    public String generateModel() throws ParseException {

        LocalDateTime localtDateAndTime = LocalDateTime.now();
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime dateAndTime  = ZonedDateTime.of(localtDateAndTime, zoneId);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Brand brand = new Brand();

        brand.setBrandName("Nissan");
        brand.setLaunchDate(df.parse("1945-02-12"));

        Brand brand2 = new Brand();

        brand2.setBrandName("Honda");
        brand2.setLaunchDate(df.parse("1942-05-25"));

        Brand brand3 = new Brand();

        brand3.setBrandName("BMW");
        brand3.setLaunchDate(df.parse("1939-07-19"));

        Model model = new Model();

        model.setModelName("M4");
        model.setReleaseDate("2015");
        model.setBrand(brand3);

        Model model2 = new Model();

        model2.setModelName("NSX");
        model2.setReleaseDate("2000");
        model2.setBrand(brand2);

        Model model3 = new Model();

        model3.setModelName("GTR");
        model3.setReleaseDate("2018");
        model3.setBrand(brand);


        Car car = new Car();
        car.setVin("W23AGWEHW452SAXFS");
        car.setCountryOfOrigin("Polska");
        car.setMileage((float)15250);
        car.setProductionDate(df.parse("2015-05-10"));
        car.setTransmissionType(Car.TransmissionType.AUTOMATYCZNA);
        car.setModel(model);
        model.setSamochod(Set.of(car));

        Car car2 = new Car();
        car2.setVin("WKS82FHW6A7WMBCSG");
        car2.setCountryOfOrigin("Niemcy");
        car2.setMileage(150000);
        car2.setProductionDate(df.parse("2010-12-15"));
        car2.setTransmissionType(Car.TransmissionType.MANUALNA);
        car2.setModel(model2);
        model2.setSamochod(Set.of(car2));

        Car car3 = new Car();
        car3.setVin("H25AMCHSTW03672AS");
        car3.setCountryOfOrigin("Polska");
        car3.setMileage((float)25000.25);
        car3.setProductionDate(df.parse("2020-10-10"));
        car3.setTransmissionType(Car.TransmissionType.AUTOMATYCZNA);
        car3.setModel(model3);
        model3.setSamochod(Set.of(car3));

        Engine engine = new Engine();

        engine.setFuelType(Engine.FuelType.PETROL);
        engine.setHorsepower(500);
        engine.setEngineCapacity(BigDecimal.valueOf(2.5));
        car.setSilnik(engine);

        Engine engine2 = new Engine();

        engine2.setFuelType(Engine.FuelType.ELECTRIC);
        engine2.setHorsepower(400);
        car2.setSilnik(engine2);

        Engine engine3 = new Engine();

        engine3.setFuelType(Engine.FuelType.DIESEL);
        engine3.setHorsepower(350);
        engine3.setEngineCapacity(BigDecimal.valueOf(3.0));
        car3.setSilnik(engine3);

        Driver driver = new Driver();
        driver.setPesel("99123242156");
        driver.setName("Jan");
        driver.setSurname("Kowalski");
        car.setDriver(driver);

        Driver driver2 = new Driver();
        driver2.setPesel("52729562452");
        driver2.setName("Leon");
        driver2.setSurname("Zawodowiec");
        car2.setDriver(driver2);

        driverService.saveDriver(driver);
        driverService.saveDriver(driver2);
        engineService.saveEngine(engine);
        engineService.saveEngine(engine2);
        engineService.saveEngine(engine3);
        brandService.saveBrand(brand);
        brandService.saveBrand(brand2);
        brandService.saveBrand(brand3);
        modelService.saveModel(model);
        modelService.saveModel(model2);
        modelService.saveModel(model3);
        carService.saveCar(car);
        carService.saveCar(car2);
        carService.saveCar(car3);

        return "Model Generated";
    }
}
