package com.example.demo.service;

import com.example.demo.dto.DestinationDTO;

import java.util.List;

public interface DestinationService {
    List<DestinationDTO> getAllDestinations();
    DestinationDTO getDestinationById(Long id);
    DestinationDTO createDestination(DestinationDTO destinationDTO);
    DestinationDTO updateDestination(Long id, DestinationDTO destinationDTO);
    void deleteDestination(Long id);
}
