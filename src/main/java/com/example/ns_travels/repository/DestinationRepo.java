package com.example.ns_travels.repository;

import com.example.ns_travels.entity.Destination;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DestinationRepo extends JpaRepository<Destination, Long> {
    List<Destination> findByCategory(String category);
}
