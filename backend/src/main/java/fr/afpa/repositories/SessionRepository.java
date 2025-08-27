package fr.afpa.repositories;

import fr.afpa.entities.Session;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SessionRepository implements PanacheRepository<Session> {
}
