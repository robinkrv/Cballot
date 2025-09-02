package fr.afpa.services;

import fr.afpa.constants.TypeDelegue;
import fr.afpa.dto.BinomeDTO;
import fr.afpa.entities.*;
import fr.afpa.mappers.BinomeMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class BinomeService {

    @Inject
    BinomeMapper binomeMapper;

    /**
     * Crée un binôme avec principal et suppléant
     */
    @Transactional
    public BinomeDTO create(BinomeDTO dto) {
        Binome binome = binomeMapper.toEntity(dto);

        binome.scrutin = Scrutin.findById(dto.scrutinId);
        binome.persist();

        // Création du stagiaire principal
        StagiaireBinome principal = new StagiaireBinome();
        principal.stagiaire = Stagiaire.findById(dto.principalId);
        principal.binome = binome;
        principal.scrutin = binome.scrutin;
        principal.type = TypeDelegue.PRINCIPAL;
        principal.persist();

        // Création du stagiaire suppléant
        StagiaireBinome suppleant = new StagiaireBinome();
        suppleant.stagiaire = Stagiaire.findById(dto.suppleantId);
        suppleant.binome = binome;
        suppleant.scrutin = binome.scrutin;
        suppleant.type = TypeDelegue.SUPPLEANT;
        suppleant.persist();

        return toCompleteBinomeDTO(binome);
    }

    /**
     * Récupère tous les binômes d'un scrutin
     */
    public List<BinomeDTO> getByScrutin(Long scrutinId) {
        List<Binome> binomes = Binome.find("scrutin.id", scrutinId).list();
        return binomes.stream()
                .map(this::toCompleteBinomeDTO)
                .toList();
    }

    /**
     * Récupère le principal d'un binôme
     */
    public Long getPrincipalId(Long binomeId) {
        StagiaireBinome principal = StagiaireBinome.find(
                "binome.id = ?1 AND type = ?2",
                binomeId,
                TypeDelegue.PRINCIPAL).firstResult();

        return principal != null ? principal.stagiaire.id : null;
    }

    /**
     * Récupère le suppléant d'un binôme
     */
    public Long getSuppleantId(Long binomeId) {
        StagiaireBinome suppleant = StagiaireBinome.find(
                "binome.id = ?1 AND type = ?2",
                binomeId,
                TypeDelegue.SUPPLEANT).firstResult();

        return suppleant != null ? suppleant.stagiaire.id : null;
    }

    /**
     * Convertit un Binome en BinomeDTO avec les IDs du principal et suppléant
     */
    private BinomeDTO toCompleteBinomeDTO(Binome binome) {
        BinomeDTO dto = binomeMapper.toDTO(binome);
        dto.principalId = getPrincipalId(binome.id);
        dto.suppleantId = getSuppleantId(binome.id);
        return dto;
    }
}
