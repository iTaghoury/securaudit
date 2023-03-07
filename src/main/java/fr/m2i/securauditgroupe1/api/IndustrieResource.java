package fr.m2i.securauditgroupe1.api;

import fr.m2i.securauditgroupe1.data.IndustrieDA;
import fr.m2i.securauditgroupe1.exception.IdNotFoundException;
import fr.m2i.securauditgroupe1.model.Industrie;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;

@Path("/industrie")
public class IndustrieResource {

    @POST
    @Path("/create")
    public Response createIndustrie(@FormParam("siret") String siret, @FormParam("raisonSociale") String raisonSociale) {
        Industrie industrie = new Industrie(siret, raisonSociale);
        try (IndustrieDA da = new IndustrieDA()) {
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

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getIndustrieList() {
        try (IndustrieDA da = new IndustrieDA()) {
            return Response
                    .status(Response.Status.OK)
                    .entity(da.getIndustrieFromDB())
                    .build();
        } catch (SQLException e) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity(e.getMessage())
                    .build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getIndustrieById(@PathParam("id") int idIndustrie) {
        try (IndustrieDA da = new IndustrieDA()) {
            return Response
                    .status(Response.Status.OK)
                    .entity(da.getIndustrieById(idIndustrie))
                    .build();
        } catch (IdNotFoundException e1) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity(e1.getMessage())
                    .build();
        } catch (SQLException e2) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(e2.getMessage())
                    .build();
        }
    }

    @PUT
    @Path("/update")
    public Response updateIndustrie(@QueryParam("id") int idIndustrie, @FormParam("siret") String siret, @FormParam("raisonSociale") String raisonSociale) {
        Industrie industrie = new Industrie(idIndustrie, siret, raisonSociale);
        try (IndustrieDA da = new IndustrieDA()) {
            da.updateIndustrie(industrie);
            return Response
                    .status(Response.Status.OK)
                    .entity(industrie)
                    .build();
        } catch (IdNotFoundException e1) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity(e1.getMessage())
                    .build();
        } catch (SQLException e2) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(e2.getMessage())
                    .build();
        }
    }




}