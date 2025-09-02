package fr.afpa.web.controllers;

import fr.afpa.dto.ScrutinDTO;
import fr.afpa.entities.Scrutin;
import fr.afpa.services.ScrutinService;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/scrutins")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ScrutinController {

    @Inject
    ScrutinService scrutinService;

    @POST
    @Transactional
    public Response create(ScrutinDTO dto) {
        if (dto == null || dto.adminId == null || dto.sessionId == null || dto.binomes == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("adminId, sessionId et binomes sont requis").build();
        }

        ScrutinDTO scrutin = scrutinService.create(dto);
        return Response.status(Response.Status.CREATED).entity(scrutin).build();
    }

    @GET
    public List<Scrutin> getAll() {
        return Scrutin.listAll();
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") Long id) {
        Scrutin scrutin = Scrutin.findById(id);
        if (scrutin == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(scrutin).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response delete(@PathParam("id") Long id) {
        Scrutin scrutin = Scrutin.findById(id);
        if (scrutin == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        scrutin.delete();
        return Response.noContent().build();
    }

}
