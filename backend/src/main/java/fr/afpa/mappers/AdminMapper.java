package fr.afpa.mappers;

import fr.afpa.dto.AdminDTO;
import fr.afpa.entities.Admin;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "cdi")
public interface AdminMapper {
    
    // Conversion Entity vers DTO (avec données de l'utilisateur)
    @Mapping(source = "utilisateur.name_", target = "name")
    @Mapping(source = "utilisateur.firstname_", target = "firstname") 
    @Mapping(source = "utilisateur.mail", target = "mail")
    AdminDTO toDTO(Admin admin);
    
    // Conversion DTO vers Entity (partielle - sans les relations complexes)
    @Mapping(target = "utilisateur", ignore = true)
    @Mapping(target = "id", ignore = true)
    Admin toEntity(AdminDTO adminDTO);
    
    // Liste
    List<AdminDTO> toDTOList(List<Admin> admins);
    
    // Mise à jour (attention aux champs sensibles)
    @Mapping(target = "utilisateur", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", ignore = true) // Sécurité : pas de mise à jour directe du password
    void updateEntityFromDTO(AdminDTO dto, @MappingTarget Admin entity);
}
