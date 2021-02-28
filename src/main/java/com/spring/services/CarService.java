package com.spring.services;




import com.spring.entities.Car;

import java.util.Optional;

public interface CarService {

    Iterable<Car> listAllCars();

    Iterable<Car> findAllCarsPaging(Integer pageNr, Integer howManyOnPage);

    Optional<Car> getCarById(String vin);

    Car saveCar(Car car);

    void deleteCar(String vin);

    boolean checkIfExist(String vin);

    Integer averageMileage();

    Iterable<Car> findCarByVin(String vin);
}
