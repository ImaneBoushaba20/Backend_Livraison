package com.example.demo.service;

import com.example.demo.dto.ProductDTO;
import com.example.demo.dto.ReceptionDTO;
import com.example.demo.dto.ReceptionDetailDTO;
import com.example.demo.entities.Category;
import com.example.demo.entities.DetailsReceptionProduit;
import com.example.demo.entities.Product;
import com.example.demo.entities.ReceptionProduit;
import com.example.demo.repositories.DetailsReceptionProduitRepository;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.repositories.ReceptionProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReceptionService {

    private final DetailsReceptionProduitRepository detailsReceptionRepository;
    private final ReceptionProduitRepository receptionRepository;
    private final ProductRepository productRepository;


    @Autowired
    public ReceptionService(ReceptionProduitRepository receptionRepository, DetailsReceptionProduitRepository detailsReceptionRepository,ProductRepository productRepository) {
        this.receptionRepository = receptionRepository;
        this.detailsReceptionRepository = detailsReceptionRepository;
        this.productRepository=productRepository;

    }

    public ReceptionDTO createReception(ReceptionDTO receptionDTO) {
        // Créer une nouvelle réception

        ReceptionProduit reception = new ReceptionProduit();
        reception.setNomFournisseur(receptionDTO.getNomFournisseur());
        reception.setContact(receptionDTO.getContact());
        reception.setDateReception(receptionDTO.getDateReception());

        // Enregistrer la réception dans la base de données
        ReceptionProduit savedReception = receptionRepository.save(reception);

        // Ajouter les détails de réception
        // Foriegn key ( Product ID , reception ID )
        for (ReceptionDetailDTO detailDTO : receptionDTO.getDetails()) {
            DetailsReceptionProduit detail = new DetailsReceptionProduit();
            detail.setReception(savedReception);
            detail.setProduct(productRepository.findById(detailDTO.getProductId()).orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + detailDTO.getProductId())));
            detail.setQuantite(detailDTO.getQuantite());
            detail.setPrixUnitaire(detailDTO.getPrixUnitaire());
            // Enregistrer le détail dans la base de données
            detailsReceptionRepository.save(detail);
        }

        // Retourner la réception créée
        return mapToReceptionDTO(savedReception);
    }

    // Méthode pour récupérer toutes les réceptions de produits
    public List<ReceptionDTO> getAllReceptions() {
        List<ReceptionProduit> receptions = receptionRepository.findAll();
        return receptions.stream()
                .map(this::mapToReceptionDTO)
                .collect(Collectors.toList());
    }

    // Méthode pour récupérer une réception de produit par ID
    public ReceptionDTO getReceptionById(Long id) {
        ReceptionProduit reception = receptionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Reception not found with id: " + id));
        return mapToReceptionDTO(reception);
    }


    // Méthode pour mettre à jour une réception de produit existante
    public ReceptionDTO updateReception(Long id, ReceptionDTO receptionDTO) {
        ReceptionProduit existingReception = receptionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Reception not found with id: " + id));
        updateReceptionEntity(existingReception, receptionDTO);
        ReceptionProduit updatedReception = receptionRepository.save(existingReception);
        return mapToReceptionDTO(updatedReception);
    }

    // Méthode pour supprimer une réception de produit
    public void deleteReception(Long id) {
        receptionRepository.deleteById(id);
    }

    // Méthode pour mapper une entité ReceptionProduit vers ReceptionDTO
    private ReceptionDTO mapToReceptionDTO(ReceptionProduit reception) {
        ReceptionDTO dto = new ReceptionDTO();
        dto.setId(reception.getId());
        dto.setNomFournisseur(reception.getNomFournisseur());
        dto.setContact(reception.getContact());
        dto.setDateReception(reception.getDateReception());
        // Map other fields if necessary
        return dto;
    }

    // Méthode pour mapper ReceptionDTO vers une entité ReceptionProduit
    private ReceptionProduit mapToReceptionEntity(ReceptionDTO dto) {
        ReceptionProduit reception = new ReceptionProduit();
        reception.setNomFournisseur(dto.getNomFournisseur());
        reception.setContact(dto.getContact());
        reception.setDateReception(dto.getDateReception());
        // Map other fields if necessary
        return reception;
    }

    // Méthode pour mettre à jour les champs d'une entité ReceptionProduit à partir d'un ReceptionDTO
    private void updateReceptionEntity(ReceptionProduit reception, ReceptionDTO dto) {
        reception.setNomFournisseur(dto.getNomFournisseur());
        reception.setContact(dto.getContact());
        reception.setDateReception(dto.getDateReception());
        // Update other fields if necessary
    }


    // Méthode pour récupérer les produits associés à une réception spécifique
    public List<ProductDTO> getProductsForReception(Long receptionId) {
        List<DetailsReceptionProduit> details = detailsReceptionRepository.findByReception_Id(receptionId);
        return details.stream()
                .map(detail -> {
                    ProductDTO productDTO = new ProductDTO();
                    productDTO.setId(detail.getProduct().getId());
                    productDTO.setNom(detail.getProduct().getNom());
                    productDTO.setDescription(detail.getProduct().getDescription());
                    productDTO.setPrix(detail.getPrixUnitaire());
                    // Vous pouvez ajouter d'autres informations du produit au besoin
                    return productDTO;
                })
                .collect(Collectors.toList());
    }
}

