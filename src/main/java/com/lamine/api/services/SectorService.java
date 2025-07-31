package com.lamine.api.services;

import com.lamine.api.dao.ISectorDao;
import com.lamine.api.exceptions.NotFoundException;
import com.lamine.api.models.Sector;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SectorService implements ISectorService {

    private final ISectorDao sectorDao;

    @Override
    public List<Sector> getAllSectors() {
        log.info("Récupération de tous les secteurs");
        return sectorDao.findAll();
    }

    @Override
    public Sector getSectorById(Long id) {
        log.info("Récupération du secteur avec l'ID: {}", id);
        return sectorDao.findById(id)
                .orElseThrow(() -> new NotFoundException("Secteur non trouvé avec l'ID: " + id));
    }

    @Override
    public Sector createSector(Sector sector) {
        log.info("Création d'un nouveau secteur: {}", sector.getName());

        if (sectorDao.existsByNameIgnoreCase(sector.getName())) {
            throw new IllegalArgumentException("Un secteur avec ce nom existe déjà");
        }

        return sectorDao.save(sector);
    }

    @Override
    public Sector updateSector(Long id, Sector sector) {
        log.info("Mise à jour du secteur avec l'ID: {}", id);

        Sector existingSector = getSectorById(id);

        if (!existingSector.getName().equalsIgnoreCase(sector.getName()) &&
                sectorDao.existsByNameIgnoreCase(sector.getName())) {
            throw new IllegalArgumentException("Un secteur avec ce nom existe déjà");
        }

        existingSector.setName(sector.getName());
        return sectorDao.save(existingSector);
    }

    @Override
    public void deleteSector(Long id) {
        log.info("Suppression du secteur avec l'ID: {}", id);

        Sector sector = getSectorById(id);

        if (!sector.getClasses().isEmpty()) {
            throw new IllegalStateException("Impossible de supprimer un secteur qui contient des classes");
        }

        sectorDao.delete(sector);
    }
}
