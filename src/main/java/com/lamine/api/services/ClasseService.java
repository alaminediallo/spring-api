package com.lamine.api.services;

import com.lamine.api.dao.IClasseDao;
import com.lamine.api.exceptions.NotFoundException;
import com.lamine.api.models.Classe;
import com.lamine.api.models.Sector;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClasseService implements IClasseService {

    private final IClasseDao classesDao;
    private final ISectorService sectorService;

    @Override
    public List<Classe> getAllClasse() {
        log.info("Récupération de toutes les classes");
        return classesDao.findAll();
    }

    @Override
    public Classe getClasseById(Long id) {
        log.info("Récupération de la classe avec l'ID: {}", id);
        return classesDao.findById(id)
                .orElseThrow(() -> new NotFoundException("Classe non trouvée avec l'ID: " + id));
    }

    @Override
    public Classe createClasse(Classe classes) {
        log.info("Création d'une nouvelle classe: {}", classes.getClassName());

        // Vérifier que le secteur existe
        Sector sector = sectorService.getSectorById(classes.getSector().getId());
        classes.setSector(sector);

        // Vérifier l'unicité du nom de classe dans le secteur
        if (classesDao.existsByClassNameIgnoreCaseAndSectorId(
                classes.getClassName(), classes.getSector().getId())) {
            throw new IllegalArgumentException("Une classe avec ce nom existe déjà dans ce secteur");
        }

        return classesDao.save(classes);
    }

    @Override
    public Classe updateClasse(Long id, Classe classes) {
        log.info("Mise à jour de la classe avec l'ID: {}", id);

        Classe existingClass = getClasseById(id);

        // Vérifier que le secteur existe s'il a changé
        if (!existingClass.getSector().getId().equals(classes.getSector().getId())) {
            Sector sector = sectorService.getSectorById(classes.getSector().getId());
            existingClass.setSector(sector);
        }

        // Vérifier l'unicité du nom de classe dans le secteur
        if ((!existingClass.getClassName().equalsIgnoreCase(classes.getClassName()) ||
                !existingClass.getSector().getId().equals(classes.getSector().getId())) &&
                classesDao.existsByClassNameIgnoreCaseAndSectorId(
                        classes.getClassName(), classes.getSector().getId())) {
            throw new IllegalArgumentException("Une classe avec ce nom existe déjà dans ce secteur");
        }

        existingClass.setClassName(classes.getClassName());
        existingClass.setDescription(classes.getDescription());

        return classesDao.save(existingClass);
    }

    @Override
    public void deleteClasse(Long id) {
        log.info("Suppression de la classe avec l'ID: {}", id);

        Classe classes = getClasseById(id);
        classesDao.delete(classes);
    }
}