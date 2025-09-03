package fr.afpa.mappers;

import fr.afpa.dto.ScrutinDTO;
import fr.afpa.entities.Scrutin;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "jakarta", uses = { BinomeMapper.class })
public interface ScrutinMapper {

    // Conversion Entity vers DTO
    @Mapping(source = "admin.id", target = "adminId")
    @Mapping(source = "session.id", target = "sessionId")
    @Mapping(source = "binomes", target = "binomes")
    ScrutinDTO toDTO(Scrutin scrutin);

    // Conversion DTO vers Entity (partielle)
    @Mapping(target = "admin", ignore = true)
    @Mapping(target = "session", ignore = true)
    @Mapping(target = "binomes", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "autorisations", ignore = true)
    Scrutin toEntity(ScrutinDTO scrutinDTO);

    // Liste
    List<ScrutinDTO> toDTOList(List<Scrutin> scrutins);

    // Mise à jour
    @Mapping(target = "admin", ignore = true)
    @Mapping(target = "session", ignore = true)
    @Mapping(target = "binomes", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "autorisations", ignore = true)
    void updateEntityFromDTO(ScrutinDTO dto, @MappingTarget Scrutin entity);
}
