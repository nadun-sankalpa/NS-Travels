package com.example.ns_travels.service.impl;

import com.example.ns_travels.dto.GuideDTO;
import com.example.ns_travels.entity.Guide;
import com.example.ns_travels.repository.GuideRepo;
import com.example.ns_travels.service.GuideService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GuideServiceImpl implements GuideService {
    @Autowired
    private GuideRepo guideRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void save(GuideDTO guideDTO) {
        Guide guide = modelMapper.map(guideDTO, Guide.class);
        guideRepo.save(guide);
    }

    @Override
    public void update(GuideDTO guideDTO) {
        Optional<Guide> optGuide = guideRepo.findById(guideDTO.getId());
        if (optGuide.isEmpty()) {
            throw new RuntimeException("Guide not found with ID: " + guideDTO.getId());
        }

        Guide guide = modelMapper.map(guideDTO, Guide.class);
        guideRepo.save(guide);
    }

    @Override
    public void delete(Long id) {
        if (!guideRepo.existsById(id)) {
            throw new RuntimeException("Guide not found with ID: " + id);
        }
        guideRepo.deleteById(id);
    }

    @Override
    public GuideDTO getGuideById(Long id) {
        Optional<Guide> guide = guideRepo.findById(id);
        if (guide.isEmpty()) {
            throw new RuntimeException("Guide not found with ID: " + id);
        }
        return modelMapper.map(guide.get(), GuideDTO.class);
    }

    @Override
    public List<GuideDTO> getAllGuides() {
        List<Guide> guides = guideRepo.findAll();
        return guides.stream()
                .map(guide -> modelMapper.map(guide, GuideDTO.class))
                .toList();
    }
}

