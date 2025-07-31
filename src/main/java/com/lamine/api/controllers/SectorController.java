package com.lamine.api.controllers;

import com.lamine.api.dto.CreateSectorDTO;
import com.lamine.api.dto.SectorDTO;
import com.lamine.api.mappers.SectorMapper;
import com.lamine.api.models.Sector;
import com.lamine.api.services.ISectorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sectors")
@RequiredArgsConstructor
public class SectorController {

    private final ISectorService sectorService;
    private final SectorMapper sectorMapper;

    @GetMapping
    public ResponseEntity<List<SectorDTO>> getAllSectors() {
        List<Sector> sectors = sectorService.getAllSectors();
        List<SectorDTO> sectorDTOs = sectors.stream()
                .map(sectorMapper::toDTO)
                .toList();
        return ResponseEntity.ok(sectorDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SectorDTO> getSectorById(@PathVariable Long id) {
        Sector sector = sectorService.getSectorById(id);
        SectorDTO sectorDTO = sectorMapper.toDTO(sector);
        return ResponseEntity.ok(sectorDTO);
    }

    @PostMapping
    public ResponseEntity<SectorDTO> createSector(@Valid @RequestBody CreateSectorDTO createSectorDTO) {
        Sector sector = sectorMapper.toEntity(createSectorDTO);
        Sector createdSector = sectorService.createSector(sector);
        SectorDTO sectorDTO = sectorMapper.toDTO(createdSector);
        return ResponseEntity.status(HttpStatus.CREATED).body(sectorDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SectorDTO> updateSector(@PathVariable Long id,
                                               @Valid @RequestBody CreateSectorDTO createSectorDTO) {
        Sector sector = sectorMapper.toEntity(createSectorDTO);
        Sector updatedSector = sectorService.updateSector(id, sector);
        SectorDTO sectorDTO = sectorMapper.toDTO(updatedSector);
        return ResponseEntity.ok(sectorDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSector(@PathVariable Long id) {
        sectorService.deleteSector(id);
        return ResponseEntity.noContent().build();
    }
}