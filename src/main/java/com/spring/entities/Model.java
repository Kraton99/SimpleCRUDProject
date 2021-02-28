package com.spring.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity

public class Model {


    @Id @GeneratedValue
    @Column(name = "model_id")
    private Integer modelId;

    @Column(name = "model_name", nullable = false)
    private String modelName;

    @Column(name = "release_date")
    private String releaseDate;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @JsonIgnore
    @OneToMany(mappedBy = "model", fetch = FetchType.EAGER)
    private Set<Car> car = new HashSet<>();

    public Integer getModelId() {
        return modelId;
    }

    public void setModelId(Integer modelId) {
        this.modelId = modelId;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelname) {
        this.modelName = modelname;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Set<Car> getSamochod() {
        return car;
    }

    public void setSamochod(Set<Car> car) {
        this.car = car;
    }
}
