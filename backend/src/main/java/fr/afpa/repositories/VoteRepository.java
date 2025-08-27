package fr.afpa.repositories;

import fr.afpa.entities.Vote;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class VoteRepository implements PanacheRepository<Vote> {
}
