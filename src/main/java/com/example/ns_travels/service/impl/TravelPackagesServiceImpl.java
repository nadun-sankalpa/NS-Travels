package com.example.ns_travels.service.impl;

import com.example.ns_travels.dto.TravelPackagesDTO;
import com.example.ns_travels.entity.TravelPackages;
import com.example.ns_travels.repository.TravelPackagesRepo;
import com.example.ns_travels.service.TravelPackagesService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TravelPackagesServiceImpl implements TravelPackagesService {
    @Autowired
    private TravelPackagesRepo travelPackageRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void save(TravelPackagesDTO travelPackageDTO) {
        TravelPackages travelPackage = modelMapper.map(travelPackageDTO, TravelPackages.class);
        travelPackageRepo.save(travelPackage);
    }

    @Override
    public void update(TravelPackagesDTO travelPackageDTO) {
        Optional<TravelPackages> optTravelPackage = travelPackageRepo.findById(travelPackageDTO.getId());
        if (optTravelPackage.isEmpty()) {
            throw new RuntimeException("Travel package not found with ID: " + travelPackageDTO.getId());
        }

        TravelPackages travelPackage = modelMapper.map(travelPackageDTO, TravelPackages.class);
        travelPackageRepo.save(travelPackage);

    }

    @Override
    public void delete(Long id) {
        if (!travelPackageRepo.existsById(id)) {
            throw new RuntimeException("Travel package not found with ID: " + id);
        }
        travelPackageRepo.deleteById(id);
    }

    @Override
    public TravelPackagesDTO getTravelPackageById(Long id) {
        Optional<TravelPackages> travelPackage = travelPackageRepo.findById(id);
        if (travelPackage.isEmpty()) {
            throw new RuntimeException("Travel package not found with ID: " + id);
        }
        return modelMapper.map(travelPackage.get(), TravelPackagesDTO.class);
    }

    @Override
    public List<TravelPackagesDTO> getAllTravelPackages() {
        List<TravelPackages> travelPackages = travelPackageRepo.findAll();
        return travelPackages.stream()
                .map(travelPackage -> modelMapper.map(travelPackage, TravelPackagesDTO.class))
                .toList();
    }

    @Override
    public List<TravelPackagesDTO> getTravelPackagesByBudget(Double minBudget, Double maxBudget) {
        List<TravelPackages> travelPackages = travelPackageRepo.findByBudgetBetween(minBudget, maxBudget);
        return travelPackages.stream()
                .map(travelPackage -> modelMapper.map(travelPackage, TravelPackagesDTO.class))
                .toList();
    }
}
