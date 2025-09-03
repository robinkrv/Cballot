package fr.afpa.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public class SessionDTO {
    private Long id;
    private String nom;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private Long formationId;       
    private List<Long> stagiaireIds; 
    private List<Long> scrutinIds;   
    private Set<Long> adminIds;      

    // Getters & Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }
    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }
    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public Long getFormationId() {
        return formationId;
    }
    public void setFormationId(Long formationId) {
        this.formationId = formationId;
    }

    public List<Long> getStagiaireIds() {
        return stagiaireIds;
    }
    public void setStagiaireIds(List<Long> stagiaireIds) {
        this.stagiaireIds = stagiaireIds;
    }

    public List<Long> getScrutinIds() {
        return scrutinIds;
    }
    public void setScrutinIds(List<Long> scrutinIds) {
        this.scrutinIds = scrutinIds;
    }

    public Set<Long> getAdminIds() {
        return adminIds;
    }
    public void setAdminIds(Set<Long> adminIds) {
        this.adminIds = adminIds;
    }
}
