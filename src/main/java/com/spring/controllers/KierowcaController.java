package com.spring.controllers;


import com.spring.entities.Driver;
import com.spring.services.DriverServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Optional;
import java.util.Random;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class KierowcaController {

    @Autowired
    private DriverServiceImpl driverService;


    @RequestMapping(value = "/drivers", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Driver> list(Model model) {return driverService.listAllDrivers(); }


    @RequestMapping(value = "/drivers/{page}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Driver> list(@PathVariable("page") Integer pageNr, @RequestParam("size") Optional<Integer> howManyOnPage) {
        return driverService.listAllDriversPaging(pageNr, howManyOnPage.orElse(1));
    }
    @RequestMapping(value = "/driver/{pesel}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Driver> list(@PathVariable("pesel") String pesel) {
        return driverService.getDriverByPesel(pesel);
    }

    @RequestMapping(value = "/drivers/liczba", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> get() {
        return new ResponseEntity<>(driverService.countDriver(), HttpStatus.OK);
    }

    @RequestMapping(value = "/driver/{id}", method = RequestMethod.DELETE)
    public Iterable<Driver> delete(@PathVariable("id") Integer id) {
        driverService.deleteDriver(id);
        return driverService.listAllDrivers();
    }
    @RequestMapping(value = "/driver", method = RequestMethod.POST)
    public ResponseEntity<Driver> create(@RequestBody @Valid @NotNull Driver driver) {
        driver.setId(new Random().nextInt());
        driverService.saveDriver(driver);
        return ResponseEntity.ok().body(driver);
    }
    @RequestMapping(value = "/driver", method = RequestMethod.PUT)
    public ResponseEntity<Driver> edit(@RequestBody @Valid @NotNull Driver driver) {
        if(driverService.checkIfExist(driver.getPesel())) {
            driverService.saveDriver(driver);
            return ResponseEntity.ok().body(driver);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
