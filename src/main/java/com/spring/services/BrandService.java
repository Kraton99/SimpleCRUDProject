package com.spring.services;




import com.spring.entities.Brand;

import java.util.Optional;

public interface BrandService {

    Iterable<Brand> listAllBrands();

    Iterable<Brand> listAllBrandPaging(Integer pageNr, Integer howManyOnPage);

    Optional<Brand> getBrandById(Integer id);

    Iterable<Brand> getBrandByBrandName(String brandName);

    Brand saveBrand(Brand brand);

    void deleteBrand(Integer id);

    boolean checkIfExist(Integer id);

    Iterable<Brand> newestBrand();

    boolean checkIfExistByName(String brandName);
}
