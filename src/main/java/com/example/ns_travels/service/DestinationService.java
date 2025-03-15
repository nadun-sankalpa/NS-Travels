package com.example.ns_travels.service;


import com.example.ns_travels.dto.DestinationDTO;

import java.util.List;

public interface DestinationService {

    void save(DestinationDTO destinationDTO);
    void update(DestinationDTO destinationDTO);
    void delete(Long id);
    List<DestinationDTO> getAllDestinations();
    DestinationDTO getDestinationById(Long id);

}
