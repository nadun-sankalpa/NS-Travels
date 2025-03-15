package com.example.ns_travels.service;

import com.example.ns_travels.dto.ReviewDTO;

import java.util.List;

public interface ReviewService {
    void save(ReviewDTO reviewDTO);
    void update(ReviewDTO reviewDTO);
    void delete(Long id);
    ReviewDTO getReviewById(Long id);
    List<ReviewDTO> getAllReviews();
    List<ReviewDTO> getReviewsByUserId(Long userId);
}
