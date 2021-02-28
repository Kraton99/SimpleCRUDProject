package com.spring.controllers;

import com.spring.entities.Brand;
import com.spring.services.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class MarkaController {

    @Autowired
    BrandService brandService;

    @RequestMapping(value = "/brands", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Brand> list(Model model) { return brandService.listAllBrands();}

    @RequestMapping(value = "/brands/{page}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Brand> list(@PathVariable("page") Integer pageNr, @RequestParam("size") Optional<Integer> howManyOnPage) {
        return brandService.listAllBrandPaging(pageNr, howManyOnPage.orElse(1));
    }

    @RequestMapping(value = "/brand/{id}", method = RequestMethod.DELETE)
    public Iterable<Brand> delete(@PathVariable("id") Integer id) {
        brandService.deleteBrand(id);
        return brandService.listAllBrands();
    }
    @RequestMapping(value = "/brand/{nazwa}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Brand> list(@PathVariable("name") String brandName) {
        return brandService.getBrandByBrandName(brandName);
    }
    @RequestMapping(value = "/brand/nowa", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Brand> get() {
        return brandService.newestBrand();
    }
    @RequestMapping(value = "/brand", method = RequestMethod.POST)
    public ResponseEntity<Brand> create(@RequestBody @Valid @NotNull Brand brand) {
        brandService.saveBrand(brand);
        return ResponseEntity.ok().body(brand);
    }
    @RequestMapping(value = "/brand", method = RequestMethod.PUT)
    public ResponseEntity<Brand> edit(@RequestBody @Valid @NotNull Brand brand) {
        if(brandService.checkIfExist(brand.getBrandId())){
            brandService.saveBrand(brand);
            return ResponseEntity.ok().body(brand);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
