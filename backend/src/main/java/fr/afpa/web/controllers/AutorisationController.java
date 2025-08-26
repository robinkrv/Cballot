package fr.afpa.web.controllers;

import fr.afpa.dto.AutorisationDTO;
import fr.afpa.entities.Autorisation;
import fr.afpa.services.AutorisationService;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.UUID;

@Path("/autorisations")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AutorisationController {

    @Inject
    AutorisationService autorisationService;

    @POST
    @Path("/generer")
    public Response genererUuid(@QueryParam("stagiaireId") Long stagiaireId) {
        if (stagiaireId == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Le paramètre stagiaireId est requis.").build();
        }

        UUID uuid = autorisationService.genererAutorisation(stagiaireId);
        if (uuid == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Stagiaire introuvable avec id : " + stagiaireId).build();
        }

        // Récupérer l'autorisation mise à jour pour récupérer aVote
        Autorisation autorisation = Autorisation.find("uuid", uuid).firstResult();

        AutorisationDTO dto = new AutorisationDTO();
        dto.uuid = uuid;
        dto.aVote = autorisation != null && autorisation.aVote;

        return Response.ok(dto).build();
    }

    @GET
    @Path("/{uuid}")
    public Response getAutorisation(@PathParam("uuid") UUID uuid) {
        Autorisation autorisation = Autorisation.find("uuid", uuid).firstResult();
        if (autorisation == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Autorisation non trouvée pour uuid : " + uuid).build();
        }

        AutorisationDTO dto = new AutorisationDTO();
        dto.uuid = autorisation.uuid;
        dto.aVote = autorisation.aVote;

        return Response.ok(dto).build();
    }

    @DELETE
    @Path("/{uuid}")
    @Transactional
    public Response deleteAutorisation(@PathParam("uuid") UUID uuid) {
        Autorisation autorisation = Autorisation.find("uuid", uuid).firstResult();
        if (autorisation == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Autorisation non trouvée pour uuid : " + uuid).build();
        }

        autorisation.delete();
        return Response.noContent().build();
    }
}
