package fr.afpa.web.controllers;

import java.util.List;

import fr.afpa.dto.BinomeDTO;
import fr.afpa.entities.Binome;
import fr.afpa.entities.StagiaireBinome;
import fr.afpa.mappers.BinomeMapper;
import fr.afpa.services.BinomeService;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/binomes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BinomeController {

    @Inject
    BinomeService binomeService;

    @Inject
    BinomeMapper binomeMapper;

    @POST
    public Response create(BinomeDTO dto) {
        if (dto == null || dto.nom == null || dto.scrutinId == null ||
                dto.principalId == null || dto.suppleantId == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Il manque des infos").build();
        }

        BinomeDTO binome = binomeService.create(dto);
        return Response.status(Response.Status.CREATED).entity(binome).build();
    }

    @GET
    public List<Binome> getAll() {
        return Binome.listAll();
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") Long id) {
        Binome binome = Binome.findById(id);
        if (binome == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(binome).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response delete(@PathParam("id") Long id) {
        Binome binome = Binome.findById(id);
        if (binome == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Binome introuvable avec id " + id).build();
        }

        // Supprimer les StagiaireBinome liés
        List<StagiaireBinome> liens = StagiaireBinome.find("binome.id", id).list();
        for (StagiaireBinome sb : liens) {
            sb.delete();
        }

        binome.delete();
        return Response.noContent().build();
    }
}
