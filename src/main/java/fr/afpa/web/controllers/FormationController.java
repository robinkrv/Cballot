package fr.afpa.web.controllers;


import java.util.List;


import fr.afpa.entities.Formation;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/formation")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FormationController {
    
    @GET
    public List<Formation> getAll(){
        return Formation.listAll();
    }

    @GET
    @Path("/{id}")
    public Formation getById(@PathParam("id") Long id) {
        return Formation.findById(id);
    }

    @POST
    @Transactional
    public Response create(Formation formation) {
        formation.persist();
        return Response.status(Response.Status.CREATED).entity(formation).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response delete(@PathParam("id") Long id) {
        boolean deleted = Formation.deleteById(id);
        if (deleted) {
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
