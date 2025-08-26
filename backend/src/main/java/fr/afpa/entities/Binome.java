package fr.afpa.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import jakarta.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class Binome extends PanacheEntity {

    @Column(length = 100)
    public String nom;

    @ManyToOne
    @JoinColumn(name = "id_scrutin", nullable = false)
    public Scrutin scrutin;

    @OneToMany(mappedBy = "binome")
    public List<Vote> votes;

    @ManyToMany(mappedBy = "binomes")
    public Set<Stagiaire> stagiaires;
}
