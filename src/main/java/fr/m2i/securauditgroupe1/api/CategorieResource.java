package fr.m2i.securauditgroupe1.api;

import fr.m2i.securauditgroupe1.data.CategorieDA;
import fr.m2i.securauditgroupe1.exception.IdNotFoundException;
import fr.m2i.securauditgroupe1.model.Categorie;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;

@Path("/categorie")
public class CategorieResource {

    @GET
    @Path("/select")
    public Response getCategorieList() {
        try (CategorieDA da = new CategorieDA()) {
            return Response
                    .status(Response.Status.OK)
                    .entity(da.selectCategorie())
                    .build();
        } catch (SQLException e) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build();
        }
    }

    @GET
    @Path("/select/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCategorieById(@PathParam("id") int id) {
        try (CategorieDA da = new CategorieDA()) {
            return Response
                    .status(Response.Status.OK)
                    .entity(da.selectByIdCategorie(id))
                    .build();
        } catch (IdNotFoundException e) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity(e.getMessage())
                    .build();
        }
        catch (SQLException e1) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(e1.getMessage())
                    .build();
        }
    }
    @DELETE
    @Path("/delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteCategorieById(@PathParam("id") int id) {
        try (CategorieDA da = new CategorieDA()) {
            return Response
                    .status(Response.Status.OK)
                    .entity(da.deleteCategorie(id))
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
    public Response addCategorie(Categorie Categorie) {
        try (CategorieDA da = new CategorieDA()) {
            return Response
                    .status(Response.Status.OK)
                    .entity(da.insertionCategorie(Categorie))
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
    public Response updateCategorie(@PathParam("id") int id,Categorie Categorie) {
        try (CategorieDA da = new CategorieDA()) {
            return Response
                    .status(Response.Status.OK)
                    .entity(da.updateCategorie(Categorie,id))
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

