package fr.afpa.services;

import fr.afpa.entities.Autorisation;
import fr.afpa.entities.Stagiaire;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.UUID;

@ApplicationScoped
public class AutorisationService {

    @Transactional
    public UUID genererAutorisation(Long stagiaireId) {
        Stagiaire stagiaire = Stagiaire.findById(stagiaireId);
        if (stagiaire == null) return null;

        Autorisation autorisation = stagiaire.autorisation;

        if (autorisation == null) {
            autorisation = new Autorisation();
            autorisation.stagiaire = stagiaire;
            stagiaire.autorisation = autorisation;
        }

        UUID uuid = UUID.randomUUID();
        autorisation.uuid = uuid;
        autorisation.aVote = false;

        autorisation.persist();

        return uuid;
    }
}
