package com.lamine.api.services;

import com.lamine.api.models.Sector;

import java.util.List;

public interface ISectorService {

    List<Sector> getAllSectors();

    Sector getSectorById(Long id);

    Sector createSector(Sector sector);

    Sector updateSector(Long id, Sector sector);

    void deleteSector(Long id);
}