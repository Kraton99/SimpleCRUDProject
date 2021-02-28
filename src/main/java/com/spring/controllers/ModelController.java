package com.spring.controllers;

import com.spring.entities.Model;
import com.spring.services.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ModelController {

    @Autowired
    ModelService modelService;


    @RequestMapping(value = "/models", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Model> list(org.springframework.ui.Model model) {
        return modelService.listAllModel();
    }
    @RequestMapping(value = "/models/{page}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Model> list(@PathVariable("page") Integer pageNr, @RequestParam("size") Optional<Integer> howManyOnPage) {
        return modelService.findAllModelPaging(pageNr, howManyOnPage.orElse(1));
    }
    @RequestMapping(value = "/model/{id}", method = RequestMethod.DELETE)
    public Iterable<Model> delete(@PathVariable("id") Integer id) {
        modelService.deleteModel(id);
        return modelService.listAllModel();
    }

    @RequestMapping(value = "/model/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Model> list(@PathVariable("name") String modelName) {
        return modelService.getModelByModelName(modelName);
    }
    @RequestMapping(value = "/model", method = RequestMethod.POST)
        public ResponseEntity<Model> create(@RequestBody @Valid @NotNull Model model) {
                modelService.saveModel(model);
                return ResponseEntity.ok().body(model);
        }
    @RequestMapping(value = "/model", method = RequestMethod.PUT)
    public ResponseEntity<Model> edit(@RequestBody @Valid @NotNull Model model) {
        if(modelService.checkIfExist(model.getModelId())) {
            modelService.saveModel(model);
            return ResponseEntity.ok().body(model);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

}
