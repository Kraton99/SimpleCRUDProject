package com.spring.repositories;





import com.spring.entities.Driver;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface DriverRepository extends CrudRepository<Driver, Integer>, PagingAndSortingRepository<Driver, Integer> {

    @Query("select count(*) from Driver k where k.pesel = ?1")
    Integer checkIfExist(String pesel);

    @Query("Select count(*) from Driver k")
    Integer countDrivers();

    Iterable<Driver> findDriverByPesel(String pesel);

}
