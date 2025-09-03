package fr.afpa.web.controllers;

import fr.afpa.dto.SessionDTO;
import fr.afpa.entities.Formation;
import fr.afpa.entities.Session;
import fr.afpa.mappers.SessionMapper;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/sessions")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SessionController {

    @Inject
    SessionMapper sessionMapper;

    @GET
    public List<SessionDTO> getAll() {
        List<Session> sessions = Session.listAll();
        return sessionMapper.toDTOList(sessions);
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") Long id) {
        Session session = Session.findById(id);
        if (session == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(sessionMapper.toDTO(session)).build();
    }

    @POST
    @Transactional
    public Response create(SessionDTO dto) {
        Session session = new Session();
        session.nom = dto.getNom();
        session.dateDebut = dto.getDateDebut();
        session.dateFin = dto.getDateFin();

        Formation formation = Formation.findById(dto.getFormationId());
        if (formation == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Formation inexistante")
                    .build();
        }
        session.formation = formation;

        session.persist();
        return Response.status(Response.Status.CREATED)
                .entity(sessionMapper.toDTO(session))
                .build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response update(@PathParam("id") Long id, SessionDTO dto) {
        Session session = Session.findById(id);
        if (session == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        sessionMapper.updateEntityFromDTO(dto, session);
        return Response.ok(sessionMapper.toDTO(session)).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response delete(@PathParam("id") Long id) {
        boolean deleted = Session.deleteById(id);
        if (!deleted) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.noContent().build();
    }
}
