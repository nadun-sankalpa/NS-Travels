package com.example.ns_travels.repository;

import com.example.ns_travels.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface VehicleRepo extends JpaRepository<Vehicle, Long> {
    List<Vehicle> findByAvailability(boolean availability);

    List<Vehicle> findByType(String type);

    List<Vehicle> findByCapacityGreaterThanEqual(int minCapacity);
}
