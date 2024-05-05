package com.example.demo.entities;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "details_reception_produit")
public class DetailsReceptionProduit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "reception_produit_id")
    private ReceptionProduit reception;


    @ManyToOne
    @JoinColumn(name = "produit_id")
    private Product product;

    @Column(name = "quantite")
    private int quantite;

    @Column(name = "prix_unitaire")
    private double prixUnitaire;

}
