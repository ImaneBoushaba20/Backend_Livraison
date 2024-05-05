package com.example.demo.service;

import com.example.demo.dto.LivraisonDetailDTO;
import com.example.demo.dto.LivraisonDTO;
import com.example.demo.dto.LivraisonDetailDTO;
import com.example.demo.dto.ProductDTO;
import com.example.demo.entities.DetailsLivraisonProduit;
import com.example.demo.entities.LivraisonProduit;
import com.example.demo.entities.Product;
import com.example.demo.repositories.DetailsLivraisonRepository;
import com.example.demo.repositories.DetailsLivraisonRepository;
import com.example.demo.repositories.LivraisonRepository;
import com.example.demo.repositories.LivraisonRepository;
import com.example.demo.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import javax.persistence.EntityNotFoundException;
import jakarta.persistence.*;

import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LivraisonService {

    private final DetailsLivraisonRepository detailsLivraisonRepository;
    private final LivraisonRepository livraisonRepository;
    private final ProductRepository productRepository;

    @Autowired
    public LivraisonService(LivraisonRepository livraisonRepository, DetailsLivraisonRepository detailsLivraisonRepository, ProductRepository productRepository) {
        this.livraisonRepository = livraisonRepository;
        this.detailsLivraisonRepository = detailsLivraisonRepository;
        this.productRepository = productRepository;
    }

    public LivraisonDTO createLivraison(LivraisonDTO livraisonDTO) {
        // Créer une nouvelle livraison
        LivraisonProduit livraison = new LivraisonProduit();
        livraison.setId(livraisonDTO.getDestinationId());
        livraison.setDateLivraison(Instant.now());

        // Enregistrer la livraison dans la base de données
        LivraisonProduit savedLivraison = livraisonRepository.save(livraison);


        // Ajouter les détails de la livraison
        for (LivraisonDetailDTO detailDTO : livraisonDTO.getDetails()) {
            DetailsLivraisonProduit detail = new DetailsLivraisonProduit();
            detail.setLivraisonProduit(savedLivraison);
            detail.setProduct(productRepository.findById(detailDTO.getProduitId()).orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + detailDTO.getProduitId())));
            detail.setQuantite(detailDTO.getQuantite());
            detail.setPrixUnitaire(detailDTO.getPrixUnitaire());
            // Enregistrer le détail dans la base de données
            detailsLivraisonRepository.save(detail);
        }

        // Retourner la livraison créée
        return mapToLivraisonDTO(savedLivraison);
    }

    public List<LivraisonDTO> getAllLivraisons() {
        List<LivraisonProduit> livraisons = livraisonRepository.findAll();
        return livraisons.stream()
                .map(this::mapToLivraisonDTO)
                .collect(Collectors.toList());
    }

    public LivraisonDTO getLivraisonById(Long id) {
        LivraisonProduit livraison = livraisonRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Livraison not found with id: " + id));
        return mapToLivraisonDTO(livraison);
    }

    public LivraisonDTO updateLivraison(Long id, LivraisonDTO livraisonDTO) {
        LivraisonProduit existingLivraison = livraisonRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Livraison not found with id: " + id));
        updateLivraisonEntity(existingLivraison, livraisonDTO);
        LivraisonProduit updatedLivraison = livraisonRepository.save(existingLivraison);
        return mapToLivraisonDTO(updatedLivraison);
    }

    public void deleteLivraison(Long id) {
        livraisonRepository.deleteById(id);
    }

    private LivraisonDTO mapToLivraisonDTO(LivraisonProduit livraison) {
        LivraisonDTO dto = new LivraisonDTO();
        dto.setId(livraison.getId());
        dto.setDestinationId(livraison.getId());
        dto.setDateLivraison(Instant.now());
        // Autres mappings si nécessaire
        return dto;
    }

    private void updateLivraisonEntity(LivraisonProduit livraison, LivraisonDTO dto) {
        livraison.setId(dto.getDestinationId());
        livraison.setDateLivraison(Instant.now());
        // Autres mises à jour si nécessaire
    }

    public List<ProductDTO> getProductsForLivraison(Long livraisonId) {
        List<DetailsLivraisonProduit> details = detailsLivraisonRepository.findByLivraisonProduit_Id(livraisonId);
        return details.stream()
                .map(detail -> {
                    ProductDTO productDTO = new ProductDTO();
                    productDTO.setId(detail.getProduct().getId());
                    productDTO.setNom(detail.getProduct().getNom());
                    productDTO.setDescription(detail.getProduct().getDescription());
                    productDTO.setPrix(detail.getPrixUnitaire());
                    // Autres infos du produit si nécessaire
                    return productDTO;
                })
                .collect(Collectors.toList());
    }
}
