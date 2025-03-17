package com.example.ns_travels.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public class VehicleDTO {
    private Long id;
    private String type;
    private String model;
    private int capacity;
    private double pricePerDay;
    private boolean availability;

    public VehicleDTO() {
    }

    public VehicleDTO(Long id, String type, String model, int capacity, double pricePerDay, boolean availability) {
        this.id = id;
        this.type = type;
        this.model = model;
        this.capacity = capacity;
        this.pricePerDay = pricePerDay;
        this.availability = availability;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    @Override
    public String toString() {
        return "VehicleDTO{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", model='" + model + '\'' +
                ", capacity=" + capacity +
                ", pricePerDay=" + pricePerDay +
                ", availability=" + availability +
                '}';
    }
}
