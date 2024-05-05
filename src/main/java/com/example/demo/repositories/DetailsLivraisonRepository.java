package com.example.demo.repositories;


import com.example.demo.entities.DetailsLivraisonProduit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DetailsLivraisonRepository extends JpaRepository<DetailsLivraisonProduit, Long> {
    List<DetailsLivraisonProduit> findByLivraisonProduit_Id(Long livraisonProduitId);
}