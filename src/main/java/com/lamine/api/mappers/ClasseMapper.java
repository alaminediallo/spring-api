package com.lamine.api.mappers;

import com.lamine.api.dto.ClasseDTO;
import com.lamine.api.dto.CreateClasseDTO;
import com.lamine.api.models.Classe;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ClasseMapper {

    @Mapping(source = "sector.id", target = "sectorId")
    ClasseDTO toDTO(Classe classe);

    @Mapping(source = "sectorId", target = "sector.id")
    Classe toEntity(CreateClasseDTO createClasseDTO);
}
