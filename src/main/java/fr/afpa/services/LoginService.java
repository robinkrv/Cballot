package fr.afpa.services;

import fr.afpa.dto.LoginDTO;
import fr.afpa.entities.Admin;
import fr.afpa.entities.Utilisateur;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotAuthorizedException;

@ApplicationScoped
public class LoginService {

     @Transactional
    public Admin login(LoginDTO dto) {
        
        Utilisateur utilisateur = Utilisateur.find("mail", dto.getMail()).firstResult();

        if (utilisateur == null) {
            throw new NotAuthorizedException("Utilisateur inexistant");
        }

        Admin admin = utilisateur.admin;
        if (admin == null) {
            throw new NotAuthorizedException("Cet utilisateur n'est pas un administrateur");
        }

        if (!admin.password.equals(dto.getPassword())) {
            throw new NotAuthorizedException("Mot de passe incorrect");
        }

        return admin; 
    }
}
