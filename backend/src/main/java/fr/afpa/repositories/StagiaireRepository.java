package fr.afpa.repositories;

import fr.afpa.entities.Stagiaire;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class StagiaireRepository implements PanacheRepository<Stagiaire> {
}
