package com.spring;

import com.spring.entities.Driver;
import com.spring.entities.Car;
import com.spring.services.DriverService;
import com.spring.services.CarService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

@RunWith(SpringRunner.class)
@SpringBootTest
public class addTest {

        @Autowired
        CarService carService;

        @Autowired
        DriverService driverService;

        @Test
        public void addAndDeleteSamochodTest() throws ParseException {
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                Car car = new Car();
                car.setVin("W23AGWEHW452SAXFS");
                car.setCountryOfOrigin("Polska");
                car.setMileage((float)15250);
                car.setProductionDate(df.parse("2015-05-10"));
                car.setTransmissionType(Car.TransmissionType.AUTOMATYCZNA);
                carService.saveCar(car);
                ArrayList<Car> cars = new ArrayList<>();
                for(Car car1 : carService.listAllCars()){
                        cars.add(car1);
                }
                Assert.assertEquals(1, cars.size());
                carService.deleteCar(car.getVin());
                cars = new ArrayList<>();
                for(Car car1 : carService.listAllCars()){
                        cars.add(car1);
                }
                Assert.assertEquals(0, cars.size());
        }
        @Test
        public void searchTest() {
                Driver driver = new Driver();
                driver.setPesel("99123242156");
                driver.setName("Jan");
                driver.setSurname("Kowalski");
                driverService.saveDriver(driver);
                Driver driver2 = new Driver();
                driver2.setPesel("52729562452");
                driver2.setName("Leon");
                driver2.setSurname("Zawodowiec");
                driverService.saveDriver(driver2);
                Iterable<Driver> kierowcy = driverService.getDriverByPesel("99123242156");
                ArrayList<String> kierowcaArrayList = new ArrayList<>();
                for(Driver driver1 : kierowcy){
                        kierowcaArrayList.add(driver1.getName());
                }
                Assert.assertEquals("Jan", kierowcaArrayList.get(0));
        }
}