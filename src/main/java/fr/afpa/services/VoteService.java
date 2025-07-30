package fr.afpa.services;

import fr.afpa.dto.VoteDTO;
import fr.afpa.entities.*;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class VoteService {

    @Transactional
    public boolean enregistrerVote(VoteDTO dto) {
        Autorisation autorisation = Autorisation.find("uuid", dto.uuid).firstResult();

        if (autorisation == null || autorisation.aVote) return false;

        Vote vote = new Vote();
        vote.binome = Binome.findById(dto.binomeId);
        vote.autorisation = autorisation;

        vote.persist();

        autorisation.aVote = true;
        autorisation.uuid = null; // anonymisation
        autorisation.stagiaire = null;

        autorisation.persist();
        return true;
    }
}
