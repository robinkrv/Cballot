package fr.afpa.services;

import fr.afpa.dto.VoteDTO;
import fr.afpa.entities.*;
import fr.afpa.mappers.VoteMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class VoteService {

    @Inject
    VoteMapper voteMapper;

    /**
     * Enregistre un vote
     */
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

    /**
     * Récupère tous les votes d'un binôme
     */
    public List<VoteDTO> getVotesByBinome(Long binomeId) {
        List<Vote> votes = Vote.find("binome.id", binomeId).list();
        return voteMapper.toDTOList(votes);
    }

    /**
     * Récupère tous les votes d'un scrutin
     */
    public List<VoteDTO> getVotesByScrutin(Long scrutinId) {
        List<Vote> votes = Vote.find("binome.scrutin.id", scrutinId).list();
        return voteMapper.toDTOList(votes);
    }

    /**
     * Compte le nombre de votes pour un binôme
     */
    public long countVotesByBinome(Long binomeId) {
        return Vote.count("binome.id", binomeId);
    }

    /**
     * Vérifie si une autorisation a déjà voté
     */
    public boolean hasAlreadyVoted(UUID uuid) {
        Autorisation autorisation = Autorisation.find("uuid", uuid).firstResult();
        return autorisation != null && autorisation.aVote;
    }

    /**
     * Récupère le vote d'une autorisation spécifique
     */
    public VoteDTO getVoteByAutorisation(Long autorisationId) {
        Vote vote = Vote.find("autorisation.id", autorisationId).firstResult();
        return vote != null ? voteMapper.toDTO(vote) : null;
    }
}
