package com.example.demo.service;

import com.example.demo.dto.ReceptionDetailDTO;
import com.example.demo.entities.DetailsReceptionProduit;
import com.example.demo.repositories.DetailsReceptionProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DetailsReceptionProduitService {

    @Autowired
    private DetailsReceptionProduitRepository detailsReceptionRepository;

    public List<ReceptionDetailDTO> getAllDetailsReceptions() {
        List<DetailsReceptionProduit> detailsReceptions = detailsReceptionRepository.findAll();
        return detailsReceptions.stream()
                .map(this::mapToReceptionDetailDTO)
                .collect(Collectors.toList());
    }

    // Autres méthodes pour CRUD

    private ReceptionDetailDTO mapToReceptionDetailDTO(DetailsReceptionProduit detailsReception) {
        ReceptionDetailDTO dto = new ReceptionDetailDTO();
        dto.setId(detailsReception.getId());
        dto.setReceptionId(detailsReception.getReception().getId());
        dto.setProductId(detailsReception.getProduct().getId());
        dto.setQuantite(detailsReception.getQuantite());
        dto.setPrixUnitaire(detailsReception.getPrixUnitaire());
        return dto;
    }
}
