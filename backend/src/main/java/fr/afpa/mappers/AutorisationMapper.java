package fr.afpa.mappers;

import fr.afpa.dto.AutorisationDTO;
import fr.afpa.entities.Autorisation;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "jakarta")
public interface AutorisationMapper {
    
    // Conversion Entity vers DTO
    AutorisationDTO toDTO(Autorisation autorisation);
    
    // Conversion DTO vers Entity
    Autorisation toEntity(AutorisationDTO autorisationDTO);
    
    // Conversion d'une liste d'entités vers une liste de DTOs
    List<AutorisationDTO> toDTOList(List<Autorisation> autorisations);
    
    // Mise à jour d'une entité existante avec les données du DTO
    void updateEntityFromDTO(AutorisationDTO dto, @MappingTarget Autorisation entity);
}
