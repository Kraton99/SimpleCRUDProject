package com.spring.repositories;


import com.spring.entities.Engine;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EngineRepository extends CrudRepository<Engine, Integer>, PagingAndSortingRepository<Engine, Integer> {
    @Query("select count(*) from Engine s where s.id = ?1")
    Integer checkIfExist(Integer id);

    Iterable<Engine> findEngineByFuelType(Engine.FuelType fuelType);

    @Query("select avg(s.horsepower) from Engine s")
    Integer averageHP();
}
