package fr.afpa.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class ScrutinDTO {
    public LocalDateTime dateVote;
    public int tour;
    public Long adminId;
    public Long sessionId;
    public List<BinomeDTO> binomes;
}
