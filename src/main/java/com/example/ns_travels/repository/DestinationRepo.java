package com.example.ns_travels.repository;

import com.example.ns_travels.entity.Destination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface DestinationRepo extends JpaRepository<Destination, Long> {
    List<Destination> findByCateogary(String category);
}
