package com.example.demo.dto;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DestinationDTO {
    private Long id;
    private String name;
    private String type;
}
