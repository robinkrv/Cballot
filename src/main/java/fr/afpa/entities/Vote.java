package fr.afpa.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import jakarta.persistence.*;

@Entity
public class Vote extends PanacheEntity {

    @ManyToOne
    @JoinColumn(name = "id_binome", nullable = false)
    public Binome binome;

    @OneToOne
    @JoinColumn(name = "id_autorisation", unique = true, nullable = false)
    public Autorisation autorisation;
}
