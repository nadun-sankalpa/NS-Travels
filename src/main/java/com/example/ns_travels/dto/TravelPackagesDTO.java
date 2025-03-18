package com.example.ns_travels.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public class TravelPackagesDTO {
    private Long id;
    private String name;
    private String description;
    private double budget;
    private int duration;
    private String includedServices;

    public TravelPackagesDTO(Long id, String name, String description, double budget, int duration, String includedServices) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.budget = budget;
        this.duration = duration;
        this.includedServices = includedServices;
    }
    public TravelPackagesDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getIncludedServices() {
        return includedServices;
    }

    public void setIncludedServices(String includedServices) {
        this.includedServices = includedServices;
    }

    @Override
    public String toString() {
        return "TravelPackagesDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", budget=" + budget +
                ", duration=" + duration +
                ", includedServices='" + includedServices + '\'' +
                '}';
    }
}
