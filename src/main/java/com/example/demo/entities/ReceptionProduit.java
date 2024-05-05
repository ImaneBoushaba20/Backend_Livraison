package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.List;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "reception_produit")
public class ReceptionProduit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nom_fournisseur")
    private String nomFournisseur;

    @Column(name = "contact")
    private String contact;

    @Column(name = "date_reception")
    private Date dateReception;

    @OneToMany(mappedBy = "reception", cascade = CascadeType.ALL)
    private List<DetailsReceptionProduit> details;
}
