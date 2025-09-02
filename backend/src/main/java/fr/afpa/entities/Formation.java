package fr.afpa.entities;

import jakarta.persistence.*;
import io.quarkus.hibernate.orm.panache.PanacheEntity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Formation extends PanacheEntity {

    @Column(nullable = false, length = 100)
    public String nom;

    @OneToMany(mappedBy = "formation")
    @JsonIgnore
    public List<Session> sessions;
}
