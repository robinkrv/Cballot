package fr.afpa.mappers;

import fr.afpa.dto.LoginDTO;
import fr.afpa.entities.Admin;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "cdi")
public interface LoginMapper {
    
    // Conversion Admin vers LoginDTO (pour les tests ou autres usages)
    @Mapping(source = "utilisateur.mail", target = "mail")
    LoginDTO adminToLoginDTO(Admin admin);
    
    // Utile pour créer un LoginDTO à partir d'un email
    @Mapping(target = "password", ignore = true)
    LoginDTO createLoginDTO(String mail);
}
