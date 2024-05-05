package com.example.demo.repositories;

import com.example.demo.entities.LivraisonProduit;
import org.springframework.data.jpa.repository.JpaRepository;

    public interface LivraisonRepository extends JpaRepository<LivraisonProduit, Long> {
    }


