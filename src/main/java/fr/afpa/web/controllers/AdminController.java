package fr.afpa.web.controllers;

import fr.afpa.entities.Admin;
import fr.afpa.entities.Utilisateur;
import fr.afpa.dto.AdminDTO;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.stream.Collectors;

@Path("/admins")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AdminController {

    @GET
    public List<AdminDTO> getAll() {
        return Admin.<Admin>listAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") Long id) {
        Admin admin = Admin.findById(id);
        if (admin == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(toDTO(admin)).build();
    }

    @POST
    @Transactional
    public Response create(Admin admin) {
        if (admin.utilisateur == null || admin.utilisateur.id == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Un utilisateur valide est requis pour l'admin.").build();
        }

        Utilisateur utilisateur = Utilisateur.findById(admin.utilisateur.id);
        if (utilisateur == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Utilisateur pas trouvé pour l'id : " + admin.utilisateur.id).build();
        }

        admin.utilisateur = utilisateur;
        utilisateur.admin = admin;

        admin.persist();

        return Response.status(Response.Status.CREATED).entity(toDTO(admin)).build();
    }

    private AdminDTO toDTO(Admin admin) {
        Utilisateur utilisateur = admin.utilisateur;
        return new AdminDTO(
                admin.id,
                admin.password,
                utilisateur != null ? utilisateur.name_ : null,
                utilisateur != null ? utilisateur.firstname_ : null,
                utilisateur != null ? utilisateur.mail : null
        );
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response delete(@PathParam("id") Long id) {
        Admin admin = Admin.findById(id);
        if (admin == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        admin.delete();
        return Response.noContent().build();
    }
}
