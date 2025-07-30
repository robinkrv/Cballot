package fr.afpa.entities;

import java.io.Serializable;
import java.util.Objects;

public class ComposeId implements Serializable {

    public Long stagiaire;
    public Long binome;

    // Constructeur par défaut requis par JPA
    public ComposeId() {}

    public ComposeId(Long stagiaire, Long binome) {
        this.stagiaire = stagiaire;
        this.binome = binome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ComposeId)) return false;
        ComposeId that = (ComposeId) o;
        return Objects.equals(stagiaire, that.stagiaire) &&
               Objects.equals(binome, that.binome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stagiaire, binome);
    }
}
