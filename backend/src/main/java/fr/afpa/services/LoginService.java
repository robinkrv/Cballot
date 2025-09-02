package fr.afpa.services;

import fr.afpa.dto.AdminDTO;
import fr.afpa.dto.LoginDTO;
import fr.afpa.entities.Admin;
import fr.afpa.entities.Utilisateur;
import fr.afpa.mappers.AdminMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotAuthorizedException;

@ApplicationScoped
public class LoginService {

    @Inject
    AdminMapper adminMapper;

    /**
     * Authentification et retour d'un AdminDTO
     */
    @Transactional
    public AdminDTO login(LoginDTO dto) {
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

        return adminMapper.toDTO(admin);
    }

    /**
     * Récupère un admin par son email
     */
    public AdminDTO getAdminByEmail(String email) {
        Utilisateur utilisateur = Utilisateur.find("mail", email).firstResult();

        if (utilisateur == null || utilisateur.admin == null) {
            return null;
        }

        return adminMapper.toDTO(utilisateur.admin);
    }

    /**
     * Change le mot de passe d'un admin
     */
    @Transactional
    public boolean changePassword(Long adminId, String oldPassword, String newPassword) {
        Admin admin = Admin.findById(adminId);
        if (admin == null) return false;

        if (!admin.password.equals(oldPassword)) {
            throw new NotAuthorizedException("Ancien mot de passe incorrect");
        }

        admin.password = newPassword;
        return true;
    }

    /**
     * Vérifie si un utilisateur est admin
     */
    public boolean isUserAdmin(String email) {
        Utilisateur utilisateur = Utilisateur.find("mail", email).firstResult();
        return utilisateur != null && utilisateur.admin != null;
    }
}
