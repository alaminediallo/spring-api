package com.lamine.api.services;

import com.lamine.api.models.Classe;

import java.util.List;

public interface IClasseService {

    List<Classe> getAllClasse();

    Classe getClasseById(Long id);

    Classe createClasse(Classe classes);

    Classe updateClasse(Long id, Classe classes);

    void deleteClasse(Long id);
}
