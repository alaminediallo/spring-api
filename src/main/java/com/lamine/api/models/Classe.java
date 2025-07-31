package com.lamine.api.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "classes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Classe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Le nom de la classe ne peut pas être vide")
    @Size(min = 2, max = 100, message = "Le nom de la classe doit contenir entre 2 et 100 caractères")
    @Column(name = "class_name", nullable = false)
    private String className;

    @Size(max = 500, message = "La description ne peut pas dépasser 500 caractères")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sector_id", nullable = false)
    @NotNull(message = "Le secteur ne peut pas être null")
    @JsonBackReference
    private Sector sector;
}
