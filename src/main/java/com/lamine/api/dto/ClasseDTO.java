package com.lamine.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClasseDTO {
    private Long id;
    private String className;
    private String description;
    private Long sectorId;
}
