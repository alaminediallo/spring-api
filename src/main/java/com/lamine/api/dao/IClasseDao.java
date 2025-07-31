package com.lamine.api.dao;

import com.lamine.api.models.Classe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IClasseDao extends JpaRepository<Classe, Long> {
    boolean existsByClassNameIgnoreCaseAndSectorId(String className, Long sectorId);
}