package com.spring.services;



import com.spring.entities.Driver;

import java.util.Optional;


public interface DriverService {
    Iterable<Driver> listAllDrivers();

    Iterable<Driver> listAllDriversPaging(Integer pageNr, Integer howManyOnPage);

    Optional<Driver> getDriverById(Integer id);

    Driver saveDriver(Driver driver);

    void deleteDriver(Integer id);

    boolean checkIfExist(String pesel);

    Integer countDriver();

   Iterable<Driver> getDriverByPesel(String pesel);
}
