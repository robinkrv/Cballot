package fr.afpa.web.controllers;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import fr.afpa.dto.VoteDTO;
import fr.afpa.entities.Vote;
import fr.afpa.services.VoteService;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/votes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class VoteAnonymeController {

    @Inject
    VoteService voteService;

    @POST
    @Path("/submit")
    public Response submitVote(VoteDTO dto) {
        if (dto == null || dto.uuid == null || dto.binomeId == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("UUID et binomeId sont requis.").build();
        }

        boolean success = voteService.enregistrerVote(dto);

        if (!success) {
            return Response.status(Response.Status.FORBIDDEN)
                    .entity("Vote non autorisé ou déjà effectué.").build();
        }

        return Response.status(Response.Status.CREATED)
                .entity("Vote enregistré avec succès.").build();
    }

     @GET
    public List<VoteDTO> getAllVotes() {
        List<Vote> votes = Vote.listAll();
        List<VoteDTO> dtos = new ArrayList<>();

        for (Vote vote : votes) {
            VoteDTO dto = new VoteDTO();
            dto.uuid = vote.uuid;

            if (vote.binome != null) {
                dto.binomeId = vote.binome.id;
            }

            dtos.add(dto);
        }

        return dtos;
    }

    @GET
    @Path("/{uuid}")
    public Response getVoteById(@PathParam("uuid") UUID uuid) {
        Vote vote = Vote.findById(uuid);
        if (vote == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        VoteDTO dto = new VoteDTO();
        dto.uuid = vote.uuid;

        if (vote.binome != null) {
            dto.binomeId = vote.binome.id;
        }

        return Response.ok(dto).build();
    }

    @DELETE
    @Path("/{uuid}")
    @Transactional
    public Response deleteVote(@PathParam("uuid") UUID uuid) {
        Vote vote = Vote.findById(uuid);
        if (vote == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        vote.delete();
        return Response.noContent().build();
    }
}