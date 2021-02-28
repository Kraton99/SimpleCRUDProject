package com.spring.services;


import com.spring.entities.Engine;
import com.spring.repositories.EngineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class EngineServiceImpl implements EngineService {

    @Autowired
    EngineRepository engineRepository;
    @Override
    public Iterable<Engine> listAllEngines() {
        return engineRepository.findAll();
    }

    @Override
    public Iterable<Engine> listAllEnginesPaging(Integer pageNr, Integer howManyOnPage) {
        return engineRepository.findAll(new PageRequest(pageNr, howManyOnPage));
    }

    @Override
    public Optional<Engine> getEngineById(Integer id) {
        return engineRepository.findById(id);
    }

    @Override
    public Engine saveEngine(Engine engine) {
        return engineRepository.save(engine);
    }

    @Override
    public void deleteEngine(Integer id) {
        engineRepository.deleteById(id);
    }

    @Override
    public boolean checkIfExist(Integer id) {
        return engineRepository.checkIfExist(id) > 0;
    }

    @Override
    public Iterable<Engine> findEngineByFuelType(Engine.FuelType fuelType) {
        return engineRepository.findEngineByFuelType(fuelType);
    }

    @Override
    public Integer averageHp() {
        return engineRepository.averageHP();
    }


}
