package com.example.ns_travels.repository;

import com.example.ns_travels.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByTravelPackageId(Long travelPackageId);

    List<Booking> findByUserId(Long userId);
}
