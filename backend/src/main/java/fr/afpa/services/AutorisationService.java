package fr.afpa.services;

import fr.afpa.dto.AutorisationDTO;
import fr.afpa.entities.Autorisation;
import fr.afpa.entities.Stagiaire;
import fr.afpa.mappers.AutorisationMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class AutorisationService {

    @Inject
    AutorisationMapper autorisationMapper;

    /**
     * Génère une autorisation pour un stagiaire
     */
    @Transactional
    public UUID genererAutorisation(Long stagiaireId) {
        Stagiaire stagiaire = Stagiaire.findById(stagiaireId);
        if (stagiaire == null) return null;

        Autorisation autorisation = stagiaire.autorisation;
        UUID uuid = UUID.randomUUID();

        autorisation.uuid = uuid;
        autorisation.aVote = false;
        autorisation.persist();

        return uuid;
    }

    /**
     * Récupère une autorisation par son UUID et la retourne en DTO
     */
    public AutorisationDTO getAutorisationByUuid(UUID uuid) {
        Autorisation autorisation = Autorisation.find("uuid", uuid).firstResult();
        return autorisationMapper.toDTO(autorisation);
    }

    /**
     * Récupère toutes les autorisations sous forme de DTOs
     */
    public List<AutorisationDTO> getAllAutorisations() {
        List<Autorisation> autorisations = Autorisation.listAll();
        return autorisationMapper.toDTOList(autorisations);
    }

    /**
     * Marque qu'un stagiaire a voté
     */
    @Transactional
    public AutorisationDTO marquerVote(UUID uuid) {
        Autorisation autorisation = Autorisation.find("uuid", uuid).firstResult();
        if (autorisation == null) return null;

        autorisation.aVote = true;
        autorisation.persist();
        return autorisationMapper.toDTO(autorisation);
    }

    /**
     * Vérifie si une autorisation existe et si elle n'a pas encore voté
     */
    public boolean peutVoter(UUID uuid) {
        Autorisation autorisation = Autorisation.find("uuid", uuid).firstResult();
        return autorisation != null && !autorisation.aVote;
    }

    /**
     * Récupère l'autorisation d'un stagiaire spécifique
     */
    public AutorisationDTO getAutorisationByStagiaire(Long stagiaireId) {
        Stagiaire stagiaire = Stagiaire.findById(stagiaireId);
        if (stagiaire == null || stagiaire.autorisation == null) return null;

        return autorisationMapper.toDTO(stagiaire.autorisation);
    }
}
