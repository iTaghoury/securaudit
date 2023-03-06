package fr.m2i.securauditgroupe1.api;

import fr.m2i.securauditgroupe1.data.AuditeurDA;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;

@Path("/auditeur")
public class AuditeurResource {
    @GET
    public Response getAuditeurList() {
        try(AuditeurDA da = new AuditeurDA()) {
            return Response
                    .status(Response.Status.OK)
                    .entity(String.format("%s", da.getAuditeurFromDB()))
                    .build();
        } catch (SQLException e) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build();
        }
    }
}
