package fr.afpa.repositories;

import fr.afpa.entities.Admin;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AdminRepository implements PanacheRepository<Admin> {
}
