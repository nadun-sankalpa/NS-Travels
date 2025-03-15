package com.example.ns_travels.repository;

import com.example.ns_travels.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HotelRepo extends JpaRepository<Hotel, Long> {
    List<Hotel> findByLocation(String location);
}
