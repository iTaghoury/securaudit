package fr.m2i.securauditgroupe1.api;

import fr.m2i.securauditgroupe1.exception.IdNotFoundException;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import fr.m2i.securauditgroupe1.data.AuditDA;
import fr.m2i.securauditgroupe1.model.Audit;
import jakarta.ws.rs.core.Response;

import java.sql.*;


@Path("/audit")
public class AuditResource {
   @GET
   @Produces(MediaType.APPLICATION_JSON)
   public Response getAllAudits() {
      try(AuditDA da = new AuditDA()) {
         return Response
                 .status(Response.Status.OK)
                 .entity(da.getAllAudits())
                 .build();
      } catch (SQLException e) {
         return Response
                 .status(Response.Status.INTERNAL_SERVER_ERROR)
                 .entity(e.getMessage())
                 .build();
      }
   }

   @GET
   @Path("/{id}")
   @Produces(MediaType.APPLICATION_JSON)
   public Response getAuditparId(@PathParam("id") int auditId) {
      try(AuditDA da = new AuditDA()) {
         return Response
                 .status(Response.Status.OK)
                 .entity(da.getAuditById(auditId))
                 .build();
      } catch (IdNotFoundException e){
         return Response
                 .status(Response.Status.NOT_FOUND)
                 .entity(e.getMessage())
                 .build();
      } catch (SQLException e) {
         return Response
                 .status(Response.Status.INTERNAL_SERVER_ERROR)
                 .entity(e.getMessage())
                 .build();
      }

   }

   @POST
   @Path("/insert")
   @Produces(MediaType.APPLICATION_JSON)
   public Response InsertAudit(@FormParam("dateAudit") Date dateaudit,
                               @FormParam("dureeAudit") int dureeaudit,
                               @FormParam("coutAudit") int coutAudit,
                               @FormParam("idindustrie") int idIndustrie,
                               @FormParam("idAuditeur") int idAuditeur) {
      try(AuditDA da = new AuditDA()) {
         Audit audit = new Audit(dateaudit, dureeaudit, coutAudit, idIndustrie, idAuditeur);
         return Response
                 .status(Response.Status.OK)
                 .entity(da.insertAudit(audit))
                 .build();
      } catch (SQLException e) {
         return Response
                 .status(Response.Status.INTERNAL_SERVER_ERROR)
                 .entity(e.getMessage())
                 .build();
      }
   }

   @PUT
   @Path("/update")
   @Produces(MediaType.APPLICATION_JSON)
   public Response updateAuditREST(@QueryParam("id") int auditId,
                                   @FormParam("dateAudit") Date dateaudit,
                                   @FormParam("dureeAudit") int dureeaudit,
                                   @FormParam("coutAudit") int coutaudit,
                                   @FormParam("Idindustrie") int idindustrie,
                                   @FormParam("IdAuditeur") int idAuditeur) {
      Audit audit = new Audit(auditId,dateaudit,dureeaudit,coutaudit,idindustrie,idAuditeur);
      try (AuditDA da = new AuditDA()) {
         da.updateAudit(audit);
         return Response
                 .status(Response.Status.OK)
                 .entity(String.format("Updated Audit with Id %d",auditId))
                 .build();
      } catch (IdNotFoundException e){
         return Response
                 .status(Response.Status.NOT_FOUND)
                 .entity(e.getMessage())
                 .build();
      } catch (SQLException e) {
         return Response
                 .status(Response.Status.INTERNAL_SERVER_ERROR)
                 .entity(e.getMessage())
                 .build();
      }
   }

   @DELETE
   @Path("/delete")
   @Produces(MediaType.APPLICATION_JSON)
   public Response deleteAudit(@QueryParam("id") int auditId){
      try (AuditDA da = new AuditDA()) {
         return Response
                 .status(Response.Status.OK)
                 .entity(da.deleteAuditById(auditId))
                 .build();
      } catch (IdNotFoundException e){
         return Response
                 .status(Response.Status.NOT_FOUND)
                 .entity(e.getMessage())
                 .build();
      } catch (SQLException e) {
         return Response
                 .status(Response.Status.INTERNAL_SERVER_ERROR)
                 .entity(e.getMessage())
                 .build();
      }
   }

}