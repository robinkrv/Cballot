package fr.afpa.mappers;

import fr.afpa.dto.BinomeDTO;
import fr.afpa.entities.Binome;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "cdi")
public interface BinomeMapper {

    // Conversion Entity vers DTO
    @Mapping(source = "scrutin.id", target = "scrutinId")
    @Mapping(target = "principalId", ignore = true)
    @Mapping(target = "suppleantId", ignore = true)
    BinomeDTO toDTO(Binome binome);

    // Conversion DTO vers Entity (mapping partiel)
    @Mapping(target = "scrutin", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "stagiaires", ignore = true)
    Binome toEntity(BinomeDTO binomeDTO);

    // Conversion d'une liste d'entités vers une liste de DTOs
    List<BinomeDTO> toDTOList(List<Binome> binomes);

    // Mise à jour d'une entité existante avec les données du DTO
    @Mapping(target = "scrutin", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "stagiaires", ignore = true)
    void updateEntityFromDTO(BinomeDTO dto, @MappingTarget Binome entity);
}
