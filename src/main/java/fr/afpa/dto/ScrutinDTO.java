package fr.afpa.dto;

import java.time.LocalDate;
import java.util.List;

public class ScrutinDTO {
    public LocalDate dateVote;
    public int tour;
    public Long adminId;
    public Long sessionId;
    public List<BinomeDTO> binomes;
}
