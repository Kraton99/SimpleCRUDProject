package com.spring.entities;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity

public class Engine {

    @Id @GeneratedValue
    @Column(name = "engine_id")
    private Integer id;

    @Column(name = "fuel_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private FuelType fuelType;

    @Column(name = "engine_capacity")
    private BigDecimal engineCapacity;

    @Column(name = "horsepower", nullable = false)
    private Integer horsepower;


    public Integer getId() {
        return id;
    }

    public void setId(Integer engineId) {
        this.id = engineId;
    }

    public FuelType getFuelType() {
        return fuelType;
    }

    public void setFuelType(FuelType fuelType) {
        this.fuelType = fuelType;
    }

    public BigDecimal getEngineCapacity() {
        return engineCapacity;
    }

    public void setEngineCapacity(BigDecimal engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    public Integer getHorsepower() {
        return horsepower;
    }

    public void setHorsepower(Integer horsepower) {
        this.horsepower = horsepower;
    }


    public enum FuelType {
        DIESEL,
        PETROL,
        ELECTRIC
    }
}
