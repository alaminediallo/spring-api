package com.lamine.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateClasseDTO {
    @NotBlank(message = "Le nom de la classe ne peut pas être vide")
    @Size(min = 2, max = 100, message = "Le nom de la classe doit contenir entre 2 et 100 caractères")
    private String className;
    
    @Size(max = 500, message = "La description ne peut pas dépasser 500 caractères")
    private String description;
    
    @NotNull(message = "L'ID du secteur ne peut pas être null")
    private Long sectorId;
}
