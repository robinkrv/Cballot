package fr.afpa.web.controllers;

import fr.afpa.entities.Stagiaire;
import fr.afpa.entities.Utilisateur;
import fr.afpa.dto.StagiaireDTO;
import fr.afpa.dto.StagiaireInputDTO;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.stream.Collectors;

@Path("/stagiaires")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class StagiaireController {

    private StagiaireDTO toDTO(Stagiaire stagiaire) {
    Utilisateur utilisateur = stagiaire.utilisateur;
    return new StagiaireDTO(
            stagiaire.id,
            utilisateur != null ? utilisateur.name_ : null,
            utilisateur != null ? utilisateur.firstname_ : null,
            utilisateur != null ? utilisateur.mail : null
    );
}


    @GET
    @Path("/test")
    public List<String> test() {
        return List.of("un", "deux", "trois");
    }

    @GET
    public List<StagiaireDTO> getAll() {
        // listAll() retourne un List<Stagiaire>
        List<Stagiaire> list = Stagiaire.listAll();

        // map chaque Stagiaire vers StagiaireDTO
        return list.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") Long id) {
        Stagiaire stagiaire = Stagiaire.findById(id);
        if (stagiaire == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(toDTO(stagiaire)).build();
    }

   @POST
@Transactional
public Response create(StagiaireInputDTO input) {
    // Vérifier que tous les champs sont remplis
    if (input.name == null || input.firstname == null || input.mail == null ||
        input.name.isEmpty() || input.firstname.isEmpty() || input.mail.isEmpty()) {
        return Response.status(Response.Status.BAD_REQUEST)
                .entity("Tous les champs de l'utilisateur sont obligatoires").build();
    }

    // Créer l'utilisateur
    Utilisateur utilisateur = new Utilisateur();
    utilisateur.name_ = input.name;
    utilisateur.firstname_ = input.firstname;
    utilisateur.mail = input.mail;
    utilisateur.persist();

    // Créer le stagiaire et l’associer
    Stagiaire stagiaire = new Stagiaire();
    stagiaire.utilisateur = utilisateur;
    utilisateur.stagiaire = stagiaire;
    stagiaire.persist();

    // Retourner le DTO
    return Response.status(Response.Status.CREATED)
            .entity(toDTO(stagiaire))
            .build();
}

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response delete(@PathParam("id") Long id) {
        Stagiaire stagiaire = Stagiaire.findById(id);
        if (stagiaire == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        stagiaire.delete();
        return Response.noContent().build();
    }
}
