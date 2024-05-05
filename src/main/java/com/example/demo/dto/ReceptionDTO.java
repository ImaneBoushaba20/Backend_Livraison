package com.example.demo.dto;

import lombok.Data;

import java.sql.Date;
import java.util.List;

@Data
public class ReceptionDTO {
    private Long id;
    private String nomFournisseur;
    private String contact;
    private Date dateReception;
    private List<ReceptionDetailDTO> details;
}
