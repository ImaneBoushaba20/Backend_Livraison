package com.example.demo.dto;

import lombok.*;

import java.sql.Date;
import java.time.Instant;
import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LivraisonDTO {

    @Setter
    @Getter
    private List<LivraisonDetailDTO> details;
    private Long id;
        private Long destinationId;
        private Instant dateLivraison;

        // Getters and setters
    }


