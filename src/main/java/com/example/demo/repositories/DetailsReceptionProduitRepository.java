package com.example.demo.repositories;

import com.example.demo.entities.DetailsReceptionProduit;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface DetailsReceptionProduitRepository extends JpaRepository<DetailsReceptionProduit, Long> {

    List<DetailsReceptionProduit> findByReception_Id(Long receptionId);

}
