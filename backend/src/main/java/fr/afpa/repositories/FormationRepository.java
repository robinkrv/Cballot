package fr.afpa.repositories;

import fr.afpa.entities.Formation;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class FormationRepository implements PanacheRepository<Formation> {
    
}
