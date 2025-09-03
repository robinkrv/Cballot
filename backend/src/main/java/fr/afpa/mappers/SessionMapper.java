package fr.afpa.mappers;

import fr.afpa.dto.SessionDTO;
import fr.afpa.entities.Session;
import org.mapstruct.*;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "jakarta")
public interface SessionMapper {

    @Mapping(source = "formation.id", target = "formationId")
    @Mapping(target = "stagiaireIds", expression = "java(toStagiaireIds(session))")
    @Mapping(target = "scrutinIds", expression = "java(toScrutinIds(session))")
    @Mapping(target = "adminIds", expression = "java(toAdminIds(session))")
    SessionDTO toDTO(Session session);

    List<SessionDTO> toDTOList(List<Session> sessions);


    @Mapping(target = "formation", ignore = true)
    @Mapping(target = "stagiaires", ignore = true)
    @Mapping(target = "scrutins", ignore = true)
    @Mapping(target = "admins", ignore = true)
    @Mapping(target = "id", ignore = true) // Panache gère l'id
    Session toEntity(SessionDTO dto);


    @Mapping(target = "formation", ignore = true)
    @Mapping(target = "stagiaires", ignore = true)
    @Mapping(target = "scrutins", ignore = true)
    @Mapping(target = "admins", ignore = true)
    @Mapping(target = "id", ignore = true)
    void updateEntityFromDTO(SessionDTO dto, @MappingTarget Session entity);


    default List<Long> toStagiaireIds(Session session) {
        if (session.stagiaires == null) return null;
        return session.stagiaires.stream()
                .map(s -> s.id)
                .collect(Collectors.toList());
    }

    default List<Long> toScrutinIds(Session session) {
        if (session.scrutins == null) return null;
        return session.scrutins.stream()
                .map(s -> s.id)
                .collect(Collectors.toList());
    }

    default java.util.Set<Long> toAdminIds(Session session) {
        if (session.admins == null) return null;
        return session.admins.stream()
                .map(a -> a.id)
                .collect(Collectors.toSet());
    }
}
