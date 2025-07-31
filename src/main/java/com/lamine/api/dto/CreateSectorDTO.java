package com.lamine.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateSectorDTO {
    @NotBlank(message = "Le nom du secteur ne peut pas être vide")
    @Size(min = 2, max = 100, message = "Le nom du secteur doit contenir entre 2 et 100 caractères")
    private String name;
}
