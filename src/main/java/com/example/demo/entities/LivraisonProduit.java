package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.Id;

import com.example.demo.entities.Destination;

import java.sql.Date;
import java.time.Instant;
import java.util.List;


@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "livraison_produit")
public class LivraisonProduit {


    @Setter
    @Getter
    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date_livraison")
    private Instant dateLivraison;

    @ManyToOne
    @JoinColumn(name = "destination_id")
    private Destination destination;

    @OneToMany(mappedBy = "livraisonProduit", cascade = CascadeType.ALL)
    private List<DetailsLivraisonProduit> detailsLivraisonProduits;


}
