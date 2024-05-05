package com.example.demo.dto;


import lombok.Data;

@Data
public class ReceptionDetailDTO {
    private Long id;
    private Long receptionId;
    private Long productId;
    private int quantite;
    private double prixUnitaire;
}
