package fr.m2i.securauditgroupe1.api;

import fr.m2i.securauditgroupe1.data.IndustrieDA;
import fr.m2i.securauditgroupe1.model.Industrie;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;

@Path("/industrie")
public class IndustrieResource {

    @POST
    @Path("/create")
    public Response createIndustrie(@FormParam("siret") String siret, @FormParam("raisonSociale") String raisonSociale) {
        Industrie industrie = new Industrie(siret, raisonSociale);
        try(IndustrieDA da = new IndustrieDA()) {
            da.addIndustrie(industrie);
        } catch (SQLException e) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build();
        }
        return Response
                .status(Response.Status.CREATED)
                .entity(industrie)
                .build();
    }


}
