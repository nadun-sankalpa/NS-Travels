package com.example.ns_travels.service;

import com.example.ns_travels.dto.GuideDTO;

import java.util.List;

public interface GuideService {
    void save(GuideDTO guideDTO);
    void update(GuideDTO guideDTO);
    void delete(Long id);
    GuideDTO getGuideById(Long id);
    List<GuideDTO> getAllGuides();
}
