package com.spring.services;

import com.spring.entities.Model;
import com.spring.repositories.ModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class ModelServiceImpl implements ModelService {

    @Autowired
    ModelRepository modelRepository;

    @Override
    public Iterable<Model> listAllModel() {
        return modelRepository.findAll();
    }

    @Override
    public Iterable<Model> findAllModelPaging(Integer pageNr, Integer howManyOnPage) {
        return modelRepository.findAll(new PageRequest(pageNr,howManyOnPage));
    }

    @Override
    public Optional<Model> getModelById(Integer id) {
        return modelRepository.findById(id);
    }

    @Override
    public Model saveModel(Model model) {
        return modelRepository.save(model);
    }

    @Override
    public void deleteModel(Integer id) {
        modelRepository.deleteById(id);
    }

    @Override
    public boolean checkIfExist(Integer id) {
        return modelRepository.checkIfExist(id) > 0;
    }

    @Override
    public Iterable<Model> getModelByModelName(String modelName) {
        return modelRepository.findModelByModelName(modelName);
    }


}
