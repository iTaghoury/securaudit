package fr.m2i.securauditgroupe1.api;

import fr.m2i.securauditgroupe1.data.FraisDA;
import fr.m2i.securauditgroupe1.exception.IdNotFoundException;
import fr.m2i.securauditgroupe1.model.Frais;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;

@Path("/frais")
public class FraisResource {

    @GET
    @Path("/select")
    public Response getFraisList() {
        try (FraisDA da = new FraisDA()) {
            return Response
                    .status(Response.Status.OK)
                    .entity(da.selectFrais())
                    .build();
        } catch (SQLException e1) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(e1.getMessage())
                    .build();
        }
    }

    @GET
    @Path("/select/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFraisById(@PathParam("id") int id) {
        try (FraisDA da = new FraisDA()) {
            return Response
                    .status(Response.Status.OK)
                    .entity(da.selectByIdFrais(id))
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
    @DELETE
    @Path("/delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteFraisById(@PathParam("id") int id) {
        try (FraisDA da = new FraisDA()) {
            return Response
                    .status(Response.Status.OK)
                    .entity(da.deleteFrais(id))
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

    @POST
    @Path("/ajoute")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addFrais(Frais frais) {
        try (FraisDA da = new FraisDA()) {
            return Response
                    .status(Response.Status.OK)
                    .entity(da.insertionFrais(frais))
                    .build();
        } catch (SQLException e) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build();
        }
    }

    @PUT
    @Path("/update/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateFrais(@PathParam("id") int id,Frais frais) {
        try (FraisDA da = new FraisDA()) {
            return Response
                    .status(Response.Status.OK)
                    .entity(da.updateFrais(frais,id))
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

