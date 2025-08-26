package fr.afpa.web.controllers;

import fr.afpa.entities.Stagiaire;
import fr.afpa.entities.Utilisateur;
import fr.afpa.dto.StagiaireDTO;

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

    @GET
    @Path("/test")
    public List<String> test() {
        return List.of("un", "deux", "trois");
    }

    @GET
    public List<StagiaireDTO> getAll() {
        return Stagiaire.<Stagiaire>listAll().stream().map(this::toDTO).collect(Collectors.toList());
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
    public Response create(Stagiaire stagiaire) {
        if (stagiaire.utilisateur == null || stagiaire.utilisateur.id == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Un utilisateur valide est requis pour l'admin.").build();
        }

        Utilisateur utilisateur = Utilisateur.findById(stagiaire.utilisateur.id);
        if (utilisateur == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Utilisateur pas trouvé pour id : " + stagiaire.utilisateur.id).build();
        }

        stagiaire.utilisateur = utilisateur;
        utilisateur.stagiaire = stagiaire;

        stagiaire.persist();

        return Response.status(Response.Status.CREATED).entity(toDTO(stagiaire)).build();
    }

    private StagiaireDTO toDTO(Stagiaire stagiaire) {
        Utilisateur utilisateur = stagiaire.utilisateur;
        return new StagiaireDTO(
                stagiaire.id,
                utilisateur != null ? utilisateur.name_ : null,
                utilisateur != null ? utilisateur.firstname_ : null,
                utilisateur != null ? utilisateur.mail : null
        );
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
