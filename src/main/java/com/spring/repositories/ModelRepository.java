package com.spring.repositories;

import com.spring.entities.Model;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ModelRepository extends CrudRepository<Model, Integer>, PagingAndSortingRepository<Model, Integer> {

    @Query("select count(*) from Model m where m.modelId = ?1")
    Integer checkIfExist(Integer id);

    Iterable<Model> findModelByModelName(String modelName);
}
