package fr.afpa.entities;

import jakarta.persistence.*;
import io.quarkus.hibernate.orm.panache.PanacheEntity;

import java.util.List;

@Entity
public class Formation extends PanacheEntity {

    @Column(nullable = false, length = 100)
    public String nom;

    @OneToMany(mappedBy = "formation")
    public List<Session> sessions;
}
