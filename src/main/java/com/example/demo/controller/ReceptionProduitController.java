package com.example.demo.controller;

import com.example.demo.dto.ProductDTO;
import com.example.demo.dto.ReceptionDTO;
import com.example.demo.entities.Product;
import com.example.demo.service.ReceptionService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/receptions")
public class ReceptionProduitController {
    @Autowired
    private ReceptionService receptionService;

    // Endpoint pour récupérer toutes les réceptions de produits
    @GetMapping
    public ResponseEntity<List<ReceptionDTO>> getAllReceptions() {
        List<ReceptionDTO> receptions = receptionService.getAllReceptions();
        return new ResponseEntity<>(receptions, HttpStatus.OK);
    }

    // Endpoint pour récupérer une réception de produit par ID
    @GetMapping("/{id}")
    public ResponseEntity<ReceptionDTO> getReceptionById(@PathVariable Long id) {
        ReceptionDTO reception = receptionService.getReceptionById(id);
        return new ResponseEntity<>(reception, HttpStatus.OK);
    }

    // Endpoint pour créer une nouvelle réception de produit
    @PostMapping
    public ResponseEntity<ReceptionDTO> createReception(@RequestBody ReceptionDTO receptionDTO) {
        System.out.print("hhh");
        ReceptionDTO createdReception = receptionService.createReception(receptionDTO);
        return new ResponseEntity<>(createdReception, HttpStatus.CREATED);
    }


    // Endpoint pour mettre à jour une réception de produit existante
    @PutMapping("/{id}")
    public ResponseEntity<ReceptionDTO> updateReception(@PathVariable Long id, @RequestBody ReceptionDTO receptionDTO) {
        ReceptionDTO updatedReception = receptionService.updateReception(id, receptionDTO);
        return new ResponseEntity<>(updatedReception, HttpStatus.OK);
    }

    // Endpoint pour supprimer une réception de produit
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReception(@PathVariable Long id) {
        receptionService.deleteReception(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Endpoint pour récupérer les produits associés à une réception spécifique
    @GetMapping("/{receptionId}/products")
    public ResponseEntity<List<ProductDTO>> getProductsForReception(@PathVariable Long receptionId) {
        List<ProductDTO> products = receptionService.getProductsForReception(receptionId);
        return ResponseEntity.ok().body(products);
    }
}
