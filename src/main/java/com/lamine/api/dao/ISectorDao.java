package com.lamine.api.dao;

import com.lamine.api.models.Sector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ISectorDao extends JpaRepository<Sector, Long> {
    boolean existsByNameIgnoreCase(String name);
}
