package fr.afpa.services;

import fr.afpa.constants.TypeDelegue;
import fr.afpa.dto.BinomeDTO;
import fr.afpa.entities.*;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class BinomeService {

    @Transactional
    public Binome create(BinomeDTO dto) {
        Binome binome = new Binome();
        binome.nom = dto.nom;
        binome.scrutin = Scrutin.findById(dto.scrutinId);
        binome.persist();

        StagiaireBinome principal = new StagiaireBinome();
        principal.stagiaire = Stagiaire.findById(dto.principalId);
        principal.binome = binome;
        principal.scrutin = binome.scrutin;
        principal.type = TypeDelegue.PRINCIPAL;
        principal.persist();

        StagiaireBinome suppleant = new StagiaireBinome();
        suppleant.stagiaire = Stagiaire.findById(dto.suppleantId);
        suppleant.binome = binome;
        suppleant.scrutin = binome.scrutin;
        suppleant.type = TypeDelegue.SUPPLEANT;
        suppleant.persist();

        return binome;
    }
}

