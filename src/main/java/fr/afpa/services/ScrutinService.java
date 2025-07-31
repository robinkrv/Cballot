package fr.afpa.services;

import java.util.*;
import java.util.stream.Collectors;

import fr.afpa.dto.BinomeDTO;
import fr.afpa.dto.ScrutinDTO;
import fr.afpa.entities.*;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class ScrutinService {

    @Transactional
    public Scrutin create(ScrutinDTO dto) {
        Scrutin scrutin = new Scrutin();
        scrutin.dateVote = dto.dateVote;
        scrutin.tour = (short) dto.tour;
        scrutin.admin = Admin.findById(dto.adminId);
        scrutin.session = Session.findById(dto.sessionId);
        scrutin.persist();

        for (BinomeDTO binomeDto : dto.binomes) {
            binomeDto.scrutinId = scrutin.id; 
            new BinomeService().create(binomeDto);
        }

        return scrutin;
    }

    public Binome calculerGagnant(Long scrutinId) {
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

    return gagnant;
}

}
