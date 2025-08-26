package fr.afpa.entities;

import java.util.UUID;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

@Entity
public class Vote extends PanacheEntityBase {

    @Id
    @GeneratedValue
    public UUID uuid;
    
    @ManyToOne
    @JoinColumn(name = "id_binome", nullable = false)
    public Binome binome;

    @OneToOne
    @JoinColumn(name = "id_autorisation", unique = true, nullable = false)
    public Autorisation autorisation;
}
