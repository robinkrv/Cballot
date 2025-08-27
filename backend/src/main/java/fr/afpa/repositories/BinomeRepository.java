package fr.afpa.repositories;

import fr.afpa.entities.Binome;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BinomeRepository implements PanacheRepository<Binome> {
}
