package com.example.ns_travels.service;

import com.example.ns_travels.dto.TravelPackagesDTO;

import java.util.List;

public interface TravelPackagesService {
    void save(TravelPackagesDTO travelPackageDTO);
    void update(TravelPackagesDTO travelPackageDTO);
    void delete(Long id);
    TravelPackagesDTO getTravelPackageById(Long id);
    List<TravelPackagesDTO> getAllTravelPackages();
    List<TravelPackagesDTO> getTravelPackagesByBudget(Double minBudget, Double maxBudget);
}
