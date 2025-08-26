package fr.afpa.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import jakarta.persistence.*;

@Entity
public class Utilisateur extends PanacheEntity {

    @Column(name = "name_", nullable = false, length = 50)
    public String name_;

    @Column(name = "firstname_", nullable = false, length = 50)
    public String firstname_;

    @Column(nullable = false, length = 50)
    public String mail;

    @OneToOne(mappedBy = "utilisateur", cascade = CascadeType.ALL)
    public Admin admin;

    @OneToOne(mappedBy = "utilisateur", cascade = CascadeType.ALL)
    public Stagiaire stagiaire;
}
