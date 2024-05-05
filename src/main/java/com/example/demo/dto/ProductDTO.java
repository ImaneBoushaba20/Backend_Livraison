package com.example.demo.dto;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private Long id;
    private String nom;
    private String description;
    private double prix;
    private Long categoryId; // ID de la catégorie

    // Constructeurs, getters et setters
}

