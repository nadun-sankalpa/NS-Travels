package com.example.ns_travels.repository;

import com.example.ns_travels.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepo extends JpaRepository<Review, Long> {
    List<Review> findByEntityTypeAndEntityId(String entityType, Long entityId);

    List<Review> findByUserId(Long userId);
}
