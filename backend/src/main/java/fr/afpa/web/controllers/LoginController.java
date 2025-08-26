package fr.afpa.web.controllers;

import fr.afpa.dto.LoginDTO;
import fr.afpa.entities.Admin;
import fr.afpa.services.LoginService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/login")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LoginController {

    @Inject
    LoginService loginService;

    @POST
    public Response login(LoginDTO dto) {
        try {
            Admin admin = loginService.login(dto);
            return Response.ok(admin).build();
        } catch (Exception e) {
            return Response.status(Response.Status.UNAUTHORIZED)
                           .entity(e.getMessage())
                           .build();
        }
    }
}  
