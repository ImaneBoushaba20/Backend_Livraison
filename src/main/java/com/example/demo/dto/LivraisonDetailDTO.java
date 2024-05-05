package com.example.demo.dto;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LivraisonDetailDTO {

        private Long id;
        private Long livraisonId;
        private Long produitId;
        private int quantite;
        private double prixUnitaire;

        // Getters and setters
    }


