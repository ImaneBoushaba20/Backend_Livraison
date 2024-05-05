package com.example.demo.controller;

import com.example.demo.dto.ReceptionDetailDTO;
import com.example.demo.service.DetailsReceptionProduitService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/details-receptions")
public class DetailsReceptionProduitController {
    @Autowired
    private DetailsReceptionProduitService detailsReceptionService;

    @GetMapping
    public ResponseEntity<List<ReceptionDetailDTO>> getAllDetailsReceptions() {
        List<ReceptionDetailDTO> detailsReceptions = detailsReceptionService.getAllDetailsReceptions();
        return new ResponseEntity<>(detailsReceptions, HttpStatus.OK);
    }
}
