package com.example.demo.entities;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.net.ssl.SSLSession;

@Data
@Setter
@Getter
@Entity
@Table(name = "details_livraison_produit")
public class DetailsLivraisonProduit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Setter
    @Getter
    @ManyToOne
    @JoinColumn(name = "livraison_produit_id")
    private LivraisonProduit livraisonProduit;


    @ManyToOne
    @JoinColumn(name = "produit_id")
    private Product product;

    @Column(name = "quantite")
    private int quantite;

    @Column(name = "prix_unitaire")
    private double prixUnitaire;



}
