package com.lamine.api.controllers;

import com.lamine.api.dto.ClasseDTO;
import com.lamine.api.dto.CreateClasseDTO;
import com.lamine.api.mappers.ClasseMapper;
import com.lamine.api.models.Classe;
import com.lamine.api.services.IClasseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/classes")
@RequiredArgsConstructor
public class ClasseController {

    private final IClasseService classesService;
    private final ClasseMapper classeMapper;

    @GetMapping
    public ResponseEntity<List<ClasseDTO>> getAllClasse() {
        List<Classe> classes = classesService.getAllClasse();
        List<ClasseDTO> classeDTOs = classes.stream()
                .map(classeMapper::toDTO)
                .toList();
        return ResponseEntity.ok(classeDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClasseDTO> getClasseById(@PathVariable Long id) {
        Classe classe = classesService.getClasseById(id);
        ClasseDTO classeDTO = classeMapper.toDTO(classe);
        return ResponseEntity.ok(classeDTO);
    }

    @PostMapping
    public ResponseEntity<ClasseDTO> createClasse(@Valid @RequestBody CreateClasseDTO createClasseDTO) {
        Classe classe = classeMapper.toEntity(createClasseDTO);
        Classe createdClasse = classesService.createClasse(classe);
        ClasseDTO classeDTO = classeMapper.toDTO(createdClasse);
        return ResponseEntity.status(HttpStatus.CREATED).body(classeDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClasseDTO> updateClasse(@PathVariable Long id,
                                               @Valid @RequestBody CreateClasseDTO createClasseDTO) {
        Classe classe = classeMapper.toEntity(createClasseDTO);
        Classe updatedClasse = classesService.updateClasse(id, classe);
        ClasseDTO classeDTO = classeMapper.toDTO(updatedClasse);
        return ResponseEntity.ok(classeDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClasse(@PathVariable Long id) {
        classesService.deleteClasse(id);
        return ResponseEntity.noContent().build();
    }
}
