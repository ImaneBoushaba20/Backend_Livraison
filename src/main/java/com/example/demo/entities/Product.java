package com.example.demo.entities;
import com.example.demo.entities.Category;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "produit")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nom")
    private String nom;

    @Column(name = "description")
    private String description;

    @Column(name = "prix")
    private double prix;

    // Relation Many-to-One avec la classe Category
    @ManyToOne
    @JoinColumn(name = "categorie_id") // Nom de la colonne dans la table "produit" faisant référence à la table "categorie"
    private Category category;


}
