package fr.afpa.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Scrutin extends PanacheEntity {

    @Column(name = "date_vote", nullable = false)
    public LocalDateTime dateVote;

    @Column(nullable = false)
    public short tour;

    @ManyToOne
    @JoinColumn(name = "id_admin", nullable = false)
    public Admin admin;

    @OneToOne
    @JoinColumn(name = "id_session", unique = true, nullable = false)
    public Session session;

    @OneToMany(mappedBy = "scrutin")
    public List<Binome> binomes;

    @OneToMany(mappedBy = "scrutin")
    public List<Autorisation> autorisations;
}
