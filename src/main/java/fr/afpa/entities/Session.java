package fr.afpa.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
public class Session extends PanacheEntity {

    @Column(nullable = false, length = 100)
    public String nom;

    @Column(name = "date_debut", nullable = false)
    public LocalDate dateDebut;

    @Column(name = "date_fin", nullable = false)
    public LocalDate dateFin;

    @ManyToOne
    @JoinColumn(name = "id_formation", nullable = false)
    public Formation formation;

    @OneToMany(mappedBy = "session")
    public List<Stagiaire> stagiaires;

    @OneToMany(mappedBy = "session")
    public List<Scrutin> scrutins;

    @ManyToMany
    @JoinTable(
        name = "admin_sessions",
        joinColumns = @JoinColumn(name = "id_session"),
        inverseJoinColumns = @JoinColumn(name = "id_admin")
    )
    public Set<Admin> admins;
}
