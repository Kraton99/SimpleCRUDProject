package com.spring.services;


import com.spring.entities.Model;

import java.util.Optional;

public interface ModelService {

    Iterable<Model> listAllModel();

    Iterable<Model> findAllModelPaging(Integer pageNr, Integer howManyOnPage);

    Optional<Model> getModelById(Integer id);

    Model saveModel(Model model);

    void deleteModel(Integer id);

    boolean checkIfExist(Integer id);

    Iterable<Model> getModelByModelName(String modelName);
}
