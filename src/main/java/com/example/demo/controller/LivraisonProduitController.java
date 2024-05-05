package com.example.demo.controller;

import com.example.demo.dto.LivraisonDTO;
import com.example.demo.dto.ProductDTO;
import com.example.demo.service.LivraisonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/livraisons")
public class LivraisonProduitController {

    private final LivraisonService livraisonService;

    @Autowired
    public LivraisonProduitController(LivraisonService livraisonService) {
        this.livraisonService = livraisonService;
    }

    @GetMapping
    public ResponseEntity<List<LivraisonDTO>> getAllLivraisons() {
        List<LivraisonDTO> livraisons = livraisonService.getAllLivraisons();
        return new ResponseEntity<>(livraisons, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LivraisonDTO> getLivraisonById(@PathVariable Long id) {
        LivraisonDTO livraison = livraisonService.getLivraisonById(id);
        return new ResponseEntity<>(livraison, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<LivraisonDTO> createLivraison(@RequestBody LivraisonDTO livraisonDTO) {
        System.out.println("hihihihiiiiiii");
        LivraisonDTO createdLivraisonDTO = livraisonService.createLivraison(livraisonDTO);
        return new ResponseEntity<>(createdLivraisonDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LivraisonDTO> updateLivraison(@PathVariable Long id, @RequestBody LivraisonDTO livraisonDTO) {
        LivraisonDTO updatedLivraisonDTO = livraisonService.updateLivraison(id, livraisonDTO);
        return new ResponseEntity<>(updatedLivraisonDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLivraison(@PathVariable Long id) {
        livraisonService.deleteLivraison(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{livraisonId}/products")
    public ResponseEntity<List<ProductDTO>> getProductsForLivraison(@PathVariable Long livraisonId) {
        List<ProductDTO> products = livraisonService.getProductsForLivraison(livraisonId);
        return ResponseEntity.ok(products);
    }
}
