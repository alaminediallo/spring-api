package com.lamine.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SectorDTO {
    private Long id;
    private String name;
    private List<ClasseDTO> classes;
}
