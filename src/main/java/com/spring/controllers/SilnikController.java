package com.spring.controllers;

import com.spring.entities.Engine;
import com.spring.services.EngineService;
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
public class SilnikController {

    @Autowired
    EngineService engineService;

    @RequestMapping(value = "/engines", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Engine> list(Model model) { return engineService.listAllEngines();}

    @RequestMapping(value = "/engine/{fuelType}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Engine> getByPublicId(@PathVariable("fuelType") Engine.FuelType fuelType) {
        return engineService.findEngineByFuelType(fuelType);
    }
    @RequestMapping(value = "/engines/{page}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Engine> list(@PathVariable("page") Integer pageNr, @RequestParam("size") Optional<Integer> howManyOnPage) {
        return engineService.listAllEnginesPaging(pageNr, howManyOnPage.orElse(1));
    }
    @RequestMapping(value = "/engine/{id}", method = RequestMethod.DELETE)
    public Iterable<Engine> delete(@PathVariable("id") Integer id) {
        engineService.deleteEngine(id);
        return engineService.listAllEngines();
    }

    @RequestMapping(value = "/engine/horsepower", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> get() { return new ResponseEntity<>(engineService.averageHp(),HttpStatus.OK);}

    @RequestMapping(value = "/engine", method = RequestMethod.POST)
    public ResponseEntity<Engine> create(@RequestBody @Valid @NotNull Engine engine) {
        engineService.saveEngine(engine);
        return ResponseEntity.ok().body(engine);
    }
    @RequestMapping(value = "/engine", method = RequestMethod.PUT)
    public ResponseEntity<Engine> edit(@RequestBody @Valid @NotNull Engine engine) {
        if(engineService.checkIfExist(engine.getId())) {
            engineService.saveEngine(engine);
            return ResponseEntity.ok().body(engine);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
