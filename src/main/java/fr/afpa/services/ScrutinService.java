package fr.afpa.services;

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
        scrutin.dateVote = dto.dateVote.atStartOfDay();
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
}
