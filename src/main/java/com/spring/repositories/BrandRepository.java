package com.spring.repositories;


import com.spring.entities.Brand;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BrandRepository extends CrudRepository<Brand, Integer>, PagingAndSortingRepository<Brand, Integer> {

    @Query("select count(*) from Brand m where m.brandId = ?1")
    Integer checkIfExist(Integer id);

    @Query("SELECT m from Brand m where m.launchDate >= any (select max(m1.launchDate) from Brand m1)")
    Iterable<Brand> newestBrand();

    @Query("select count(*) from Brand m where m.brandName = ?1")
    Integer checkIfExistByName(String brandName);

    Iterable<Brand> findMarkaByBrandName(String brandName);
}
