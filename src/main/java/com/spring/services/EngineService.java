package com.spring.services;



import com.spring.entities.Engine;

import java.util.Optional;

public interface EngineService {

    Iterable<Engine> listAllEngines();

    Iterable<Engine> listAllEnginesPaging(Integer pageNr, Integer howManyOnPage);

    Optional<Engine> getEngineById(Integer id);

    Engine saveEngine(Engine engine);

    void deleteEngine(Integer id);

    boolean checkIfExist(Integer id);

    Iterable<Engine> findEngineByFuelType(Engine.FuelType fuelType);

    Integer averageHp();

}
