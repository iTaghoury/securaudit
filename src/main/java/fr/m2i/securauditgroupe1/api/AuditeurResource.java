package fr.m2i.securauditgroupe1.api;

import fr.m2i.securauditgroupe1.data.AuditeurDA;
import fr.m2i.securauditgroupe1.exception.IdNotFoundException;
import fr.m2i.securauditgroupe1.model.Auditeur;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;

@Path("/auditeur")
public class AuditeurResource {

    @POST
    @Path("/create")
    public Response addAuditeur(@FormParam("civilite") String civilite,
                                @FormParam("nom") String nom,
                                @FormParam("prenom") String prenom)
    {
        Auditeur auditeur = new Auditeur(civilite, nom, prenom);
        try(AuditeurDA da = new AuditeurDA()) {
            da.addAuditeurToDB(auditeur);
            return Response
                    .status(Response.Status.CREATED)
                    .entity("Creation nouvel auditeur")
                    .build();
        } catch (SQLException e) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAuditeurList() {
        try(AuditeurDA da = new AuditeurDA()) {
            return Response
                    .status(Response.Status.OK)
                    .entity(da.getAuditeurFromDB())
                    .build();
        } catch (SQLException e) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAuditeurByID(@PathParam("id") int id) {
        try(AuditeurDA da = new AuditeurDA()) {
            return Response
                    .status(Response.Status.OK)
                    .entity(da.getAuditeurById(id))
                    .build();
        } catch (IdNotFoundException e) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity(e.getMessage())
                    .build();
        } catch (SQLException e1) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(e1.getMessage())
                    .build();
        }
    }

}
