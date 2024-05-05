package com.example.demo.repositories;

import com.example.demo.entities.ReceptionProduit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReceptionProduitRepository extends JpaRepository<ReceptionProduit, Long> {
}
