package com.example.ns_travels.service.impl;

import com.example.ns_travels.dto.VehicleDTO;
import com.example.ns_travels.entity.Vehicle;
import com.example.ns_travels.repository.VehicleRepo;
import com.example.ns_travels.service.VehicleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleServiceImpl implements VehicleService {
    @Autowired
    private VehicleRepo vehicleRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void save(VehicleDTO vehicleDTO) {
        Vehicle vehicle = modelMapper.map(vehicleDTO, Vehicle.class);
        vehicleRepo.save(vehicle);
    }

    @Override
    public void update(VehicleDTO vehicleDTO) {
        Optional<Vehicle> optVehicle = vehicleRepo.findById(vehicleDTO.getId());
        if (optVehicle.isEmpty()) {
            throw new RuntimeException("Vehicle not found with ID: " + vehicleDTO.getId());
        }

        Vehicle vehicle = modelMapper.map(vehicleDTO, Vehicle.class);
        vehicleRepo.save(vehicle);
    }

    @Override
    public void delete(Long id) {
        if (!vehicleRepo.existsById(id)) {
            throw new RuntimeException("Vehicle not found with ID: " + id);
        }
        vehicleRepo.deleteById(id);
    }

    @Override
    public VehicleDTO getVehicleById(Long id) {
        Optional<Vehicle> vehicle = vehicleRepo.findById(id);
        if (vehicle.isEmpty()) {
            throw new RuntimeException("Vehicle not found with ID: " + id);
        }
        return modelMapper.map(vehicle.get(), VehicleDTO.class);
    }

    @Override
    public List<VehicleDTO> getAllVehicles() {
        List<Vehicle> vehicles = vehicleRepo.findAll();
        return vehicles.stream()
                .map(vehicle -> modelMapper.map(vehicle, VehicleDTO.class))
                .toList();
    }

    @Override
    public List<VehicleDTO> getVehiclesByType(String type) {
        List<Vehicle> vehicles = vehicleRepo.findByType(type);
        return vehicles.stream()
                .map(vehicle -> modelMapper.map(vehicle, VehicleDTO.class))
                .toList();
    }

    @Override
    public List<VehicleDTO> getVehiclesByCapacity(int minCapacity) {
        List<Vehicle> vehicles = vehicleRepo.findByCapacityGreaterThanEqual(minCapacity);
        return vehicles.stream()
                .map(vehicle -> modelMapper.map(vehicle, VehicleDTO.class))
                .toList();
    }

}
