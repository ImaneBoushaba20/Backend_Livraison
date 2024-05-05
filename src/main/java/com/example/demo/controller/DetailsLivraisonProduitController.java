package com.example.demo.controller;

import com.example.demo.dto.ReceptionDetailDTO;
import com.example.demo.dto.ReceptionDTO;
import com.example.demo.service.DetailsReceptionProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/details-livraisons")
public class DetailsLivraisonProduitController {

    private final DetailsReceptionProduitService detailsLivraisonService;

    @Autowired
    public DetailsLivraisonProduitController(DetailsReceptionProduitService detailsLivraisonService) {
        this.detailsLivraisonService = detailsLivraisonService;
    }

    @GetMapping
    public ResponseEntity<List<ReceptionDetailDTO>> getAllDetailsLivraisons() {
        List<ReceptionDetailDTO> detailsLivraisons = detailsLivraisonService.getAllDetailsReceptions();
        return new ResponseEntity<>(detailsLivraisons, HttpStatus.OK);
    }
}