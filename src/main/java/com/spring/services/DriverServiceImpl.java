package com.spring.services;

import com.spring.entities.Driver;
import com.spring.repositories.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class DriverServiceImpl implements DriverService {

    @Autowired
    DriverRepository driverRepository;
    @Override
    public Iterable<Driver> listAllDrivers() {
        return driverRepository.findAll();
    }

    @Override
    public Iterable<Driver> listAllDriversPaging(Integer pageNr, Integer howManyOnPage) {
        return driverRepository.findAll(new PageRequest(pageNr,howManyOnPage));
    }

    @Override
    public Optional<Driver> getDriverById(Integer id) {
        return driverRepository.findById(id);
    }

    @Override
    public Driver saveDriver(Driver driver) {
        return driverRepository.save(driver);
    }

    @Override
    public void deleteDriver(Integer id) {
        driverRepository.deleteById(id);
    }

    @Override
    public boolean checkIfExist(String pesel) {
        return driverRepository.checkIfExist(pesel) > 0;
    }

    @Override
    public Integer countDriver() {
        return driverRepository.countDrivers();
    }

    @Override
    public Iterable<Driver> getDriverByPesel(String pesel) {
        return driverRepository.findDriverByPesel(pesel);
    }

}
