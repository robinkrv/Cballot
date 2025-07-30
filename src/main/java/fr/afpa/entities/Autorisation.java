package fr.afpa.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import jakarta.persistence.*;

@Entity
public class Autorisation extends PanacheEntity {

    @Column(nullable = false, length = 36)
    public String UUID;

    @Column(name = "a_vote", nullable = false)
    public boolean aVote;

    @ManyToOne
    @JoinColumn(name = "id_1", nullable = false)
    public Scrutin scrutin;

    @OneToOne
    @JoinColumn(name = "id_stagiaire", unique = true, nullable = false)
    public Stagiaire stagiaire;

    @OneToOne(mappedBy = "autorisation", cascade = CascadeType.ALL)
    public Vote vote;
}
