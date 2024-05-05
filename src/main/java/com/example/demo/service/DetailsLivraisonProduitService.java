package com.example.demo.service;

import com.example.demo.dto.LivraisonDetailDTO;
import com.example.demo.entities.DetailsLivraisonProduit;
import com.example.demo.repositories.DetailsLivraisonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class DetailsLivraisonProduitService {
    @Autowired
    private DetailsLivraisonRepository detailsLivraisonRepository;

    public List<LivraisonDetailDTO> getAllDetailsLivraisons() {
        List<DetailsLivraisonProduit> detailsLivraisons = detailsLivraisonRepository.findAll();
        return detailsLivraisons.stream()
                .map(this::mapToDetailsLivraisonDTO)
                .collect(Collectors.toList());
    }

    // Autres méthodes pour CRUD

    private LivraisonDetailDTO mapToDetailsLivraisonDTO(DetailsLivraisonProduit detailsLivraisonProduit) {
        LivraisonDetailDTO dto = new LivraisonDetailDTO();
        dto.setId(detailsLivraisonProduit.getId());
        dto.setLivraisonId(detailsLivraisonProduit.getLivraisonProduit().getId());
        dto.setProduitId(detailsLivraisonProduit.getProduct().getId());
        dto.setQuantite(detailsLivraisonProduit.getQuantite());
        // Autres champs à mapper si nécessaire
        return dto;
    }
}
