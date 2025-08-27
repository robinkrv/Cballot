package fr.afpa.repositories;

import fr.afpa.entities.Scrutin;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ScrutinRepository implements PanacheRepository<Scrutin> {
}
