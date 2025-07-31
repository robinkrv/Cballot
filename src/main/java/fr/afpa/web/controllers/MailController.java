package fr.afpa.web.controllers;

import fr.afpa.services.MailService;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/mails")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MailController {

    @Inject
    MailService mailService;

    public static class LienVoteRequest {
        public String destinataire;
        public String lienVote;
    }

    public static class ConfirmationRequest {
        public String destinataire;
    }

    @POST
    @Path("/lien-vote")
    public Response envoyerLienVote(LienVoteRequest request) {
        if (request == null || request.destinataire == null || request.lienVote == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("destinataire et lienVote requis").build();
        }

        mailService.envoyerLienVote(request.destinataire, request.lienVote);
        return Response.ok().entity("Lien de vote envoyé").build();
    }

    @POST
    @Path("/confirmation")
    public Response envoyerConfirmation(ConfirmationRequest request) {
        if (request == null || request.destinataire == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("destinataire requis").build();
        }

        mailService.envoyerConfirmation(request.destinataire);
        return Response.ok().entity("Confirmation envoyée").build();
    }
}
