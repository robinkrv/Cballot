package fr.afpa.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import jakarta.persistence.*;
import java.util.Set;

@Entity
public class Stagiaire extends PanacheEntity {

    @ManyToOne
    @JoinColumn(name = "id_session")
    public Session session;

    @OneToOne
    @JoinColumn(name = "id_utilisateur", unique = true, nullable = false)
    public Utilisateur utilisateur;

    @OneToOne(mappedBy = "stagiaire", cascade = CascadeType.ALL)
    public Autorisation autorisation;

    @ManyToMany
    @JoinTable(
        name = "compose",
        joinColumns = @JoinColumn(name = "id"),
        inverseJoinColumns = @JoinColumn(name = "id_binome")
    )
    public Set<Binome> binomes;
}
