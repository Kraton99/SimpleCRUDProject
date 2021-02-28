package com.spring.services;

import com.spring.entities.Car;
import com.spring.repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    CarRepository carRepository;

    @Override
    public Iterable<Car> listAllCars() {
        return carRepository.findAll();
    }

    @Override
    public Iterable<Car> findAllCarsPaging(Integer pageNr, Integer howManyOnPage) {
        return carRepository.findAll(new PageRequest(pageNr, howManyOnPage));
    }

    @Override
    public Optional<Car> getCarById(String vin) {
        return carRepository.findById(vin);
    }

    @Override
    public Car saveCar(Car car) {
        return carRepository.save(car);
    }

    @Override
    public void deleteCar(String vin) {
        carRepository.deleteById(vin);
    }

    @Override
    public boolean checkIfExist(String vin) {
        return carRepository.checkIfExist(vin) > 0;
    }

    @Override
    public Integer averageMileage() {
        return carRepository.averageMilage();
    }

    @Override
    public Iterable<Car> findCarByVin(String vin) {
        return carRepository.findCarByVin(vin);
    }


}
