package fr.afpa.mappers;

import fr.afpa.dto.VoteDTO;
import fr.afpa.entities.Vote;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "cdi")
public interface VoteMapper {

    @Mapping(target = "uuid", source = "autorisation.uuid")
    @Mapping(target = "binomeId", source = "binome.id")
    VoteDTO toDTO(Vote vote);

    @Mapping(target = "uuid", ignore = true)
    @Mapping(target = "binome", ignore = true)
    @Mapping(target = "autorisation", ignore = true)
    Vote toEntity(VoteDTO dto);

    List<VoteDTO> toDTOList(List<Vote> votes);
}
