package fr.afpa.entities;

import jakarta.persistence.*;
import fr.afpa.constants.TypeDelegue;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

@Entity
@Table(
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"stagiaire_id", "scrutin_id"})
    }
)
public class StagiaireBinome extends PanacheEntityBase {

    @EmbeddedId
    public StagiaireBinomeId id;

    @ManyToOne
    @MapsId("stagiaireId")
    @JoinColumn(name = "stagiaire_id")
    public Stagiaire stagiaire;

    @ManyToOne
    @MapsId("binomeId")
    @JoinColumn(name = "binome_id")
    public Binome binome;

    @ManyToOne
    @JoinColumn(name = "scrutin_id", nullable = false)
    public Scrutin scrutin;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    public TypeDelegue type;
}
