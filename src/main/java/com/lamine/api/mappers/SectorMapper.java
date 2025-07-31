package com.lamine.api.mappers;

import com.lamine.api.dto.CreateSectorDTO;
import com.lamine.api.dto.SectorDTO;
import com.lamine.api.models.Sector;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {ClasseMapper.class})
public interface SectorMapper {
    SectorDTO toDTO(Sector sector);

    Sector toEntity(CreateSectorDTO createSectorDTO);
}
