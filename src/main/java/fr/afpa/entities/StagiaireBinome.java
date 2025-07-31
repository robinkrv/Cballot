package fr.afpa.entities;

import jakarta.persistence.*;
import java.io.Serializable;
import fr.afpa.constants.TypeDelegue;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;


@Entity
@IdClass(ComposeId.class)
@Table(
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"stagiaire_id", "scrutin_id"})
    }
)
public class StagiaireBinome extends PanacheEntityBase implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "stagiaire_id")
    public Stagiaire stagiaire;

    @Id
    @ManyToOne
    @JoinColumn(name = "binome_id")
    public Binome binome;

    @ManyToOne
    @JoinColumn(name = "scrutin_id", nullable = false)
    public Scrutin scrutin;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    public TypeDelegue type; // 'principal' ou 'suppleant'
}
