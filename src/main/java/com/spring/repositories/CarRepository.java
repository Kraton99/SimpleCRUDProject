package com.spring.repositories;

import com.spring.entities.Car;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CarRepository extends CrudRepository<Car, String>, PagingAndSortingRepository<Car, String> {
    @Query("select count(*) from Car s where s.vin = ?1")
    Integer checkIfExist(String vin);

    @Query("select avg(s.mileage) from Car s")
    Integer averageMilage();

    Iterable<Car> findCarByVin(String vin);

}
