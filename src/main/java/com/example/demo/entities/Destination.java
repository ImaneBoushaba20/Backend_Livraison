package com.example.demo.entities;

import lombok.*;
import jakarta.persistence.*;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "destination")
public class Destination {
    @Getter
    @Setter
    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nom")
    private String name;

    @Column(name = "type")
    private String type; // Peut être "service" ou "centre de sante"

}
