package fr.afpa.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import jakarta.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class Admin extends PanacheEntity {

    @Column(nullable = false, length = 50)
    public String password;

    @OneToOne
    @JoinColumn(name = "id_utilisateur", unique = true, nullable = false)
    public Utilisateur utilisateur;

    @OneToMany(mappedBy = "admin")
    public List<Scrutin> scrutins;

    @ManyToMany(mappedBy = "admins")
    public Set<Session> sessions;
}
