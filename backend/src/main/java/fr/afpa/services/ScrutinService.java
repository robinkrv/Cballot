package fr.afpa.services;

import fr.afpa.dto.BinomeDTO;
import fr.afpa.dto.ScrutinDTO;
import fr.afpa.entities.*;
import fr.afpa.mappers.BinomeMapper;
import fr.afpa.mappers.ScrutinMapper;
import fr.afpa.repositories.BinomeRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class ScrutinService {

    @Inject
    ScrutinMapper scrutinMapper;

    @Inject
    BinomeMapper binomeMapper;

    @Inject
    BinomeService binomeService;

    @Inject
    BinomeRepository binomeRepository;

    /**
     * Crée un nouveau scrutin
     */
    @Transactional
    public ScrutinDTO create(ScrutinDTO dto) {
        Scrutin scrutin = scrutinMapper.toEntity(dto);

        scrutin.admin = Admin.findById(dto.adminId);
        scrutin.session = Session.findById(dto.sessionId);
        scrutin.persist();

        // Création des binômes associés
        if (dto.binomes != null && !dto.binomes.isEmpty()) {
            for (BinomeDTO binomeDto : dto.binomes) {
                binomeDto.scrutinId = scrutin.id;
                binomeService.create(binomeDto);
            }
        }

        return scrutinMapper.toDTO(scrutin);
    }

    /**
     * Récupère les scrutins d'une session
     */
    public List<ScrutinDTO> getBySession(Long sessionId) {
        List<Scrutin> scrutins = Scrutin.list("session.id", sessionId);
        return scrutinMapper.toDTOList(scrutins);
    }

    /**
     * Récupère les scrutins créés par un admin
     */
    public List<ScrutinDTO> getByAdmin(Long adminId) {
        List<Scrutin> scrutins = Scrutin.list("admin.id", adminId);
        return scrutinMapper.toDTOList(scrutins);
    }

    /**
     * Calcule le gagnant d'un scrutin
     */
    public BinomeDTO calculerGagnant(Long scrutinId) {
        Scrutin scrutin = Scrutin.findById(scrutinId);
        if (scrutin == null) {
            throw new IllegalArgumentException("Scrutin non trouvé avec l'ID : " + scrutinId);
        }

        Binome gagnant = null;
        long maxVotes = -1;

        for (Binome binome : scrutin.binomes) {
            long count = binome.votes == null ? 0 : binome.votes.size();

            if (count > maxVotes) {
                gagnant = binome;
                maxVotes = count;
            }
        }

        return gagnant != null ? binomeMapper.toDTO(binomeRepository.findById(gagnant.id)) : null;
    }
}
