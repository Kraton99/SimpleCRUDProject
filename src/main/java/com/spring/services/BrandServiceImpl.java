package com.spring.services;

import com.spring.entities.Brand;
import com.spring.repositories.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    BrandRepository brandRepository;
    @Override
    public Iterable<Brand> listAllBrands() {
        return brandRepository.findAll();
    }

    @Override
    public Iterable<Brand> listAllBrandPaging(Integer pageNr, Integer howManyOnPage) {
        return brandRepository.findAll(new PageRequest(pageNr, howManyOnPage));
    }

    @Override
    public Optional<Brand> getBrandById(Integer id) {
        return brandRepository.findById(id);
    }

    @Override
    public Iterable<Brand> getBrandByBrandName(String brandName) {
        return brandRepository.findMarkaByBrandName(brandName);
    }

    @Override
    public Brand saveBrand(Brand brand) {
        return brandRepository.save(brand);
    }

    @Override
    public void deleteBrand(Integer id) {
        brandRepository.deleteById(id);
    }

    @Override
    public boolean checkIfExist(Integer id) {
        return brandRepository.checkIfExist(id) > 0;
    }

    @Override
    public Iterable<Brand> newestBrand() {
        return brandRepository.newestBrand();
    }

    @Override
    public boolean checkIfExistByName(String brandName) {
        return brandRepository.checkIfExistByName(brandName) > 0;
    }

}
