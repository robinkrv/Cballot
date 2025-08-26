package fr.afpa.entities;

import java.io.Serializable;
import java.util.Objects;
import jakarta.persistence.Embeddable;

@Embeddable
public class StagiaireBinomeId implements Serializable {

    public Long stagiaireId;
    public Long binomeId;

    public StagiaireBinomeId() {}

    public StagiaireBinomeId(Long stagiaireId, Long binomeId) {
        this.stagiaireId = stagiaireId;
        this.binomeId = binomeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StagiaireBinomeId)) return false;
        StagiaireBinomeId that = (StagiaireBinomeId) o;
        return Objects.equals(stagiaireId, that.stagiaireId) &&
               Objects.equals(binomeId, that.binomeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stagiaireId, binomeId);
    }
}
