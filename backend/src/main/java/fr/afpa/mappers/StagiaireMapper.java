package fr.afpa.mappers;

import fr.afpa.dto.StagiaireDTO;
import fr.afpa.entities.Stagiaire;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "jakarta")
public interface StagiaireMapper {
    
    // Conversion Entity vers DTO
    StagiaireDTO toDTO(Stagiaire stagiaire);
    
    // Conversion DTO vers Entity
    Stagiaire toEntity(StagiaireDTO stagiaireDTO);
    
    // Conversion d'une liste d'entités vers une liste de DTOs
    List<StagiaireDTO> toDTOList(List<Stagiaire> stagiaires);
    
    // Mise à jour d'une entité existante avec les données du DTO
    void updateEntityFromDTO(StagiaireDTO dto, @MappingTarget Stagiaire entity);
}
