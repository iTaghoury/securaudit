package fr.m2i.securauditgroupe1.api;

import fr.m2i.securauditgroupe1.exception.IdNotFoundException;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import fr.m2i.securauditgroupe1.data.AuditDA;
import fr.m2i.securauditgroupe1.model.Audit;
import jakarta.ws.rs.core.Response;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@Path("/execute")
public class AuditResource {

   private AuditDA auditDA = new AuditDA();

   public AuditResource() throws SQLException {
   }

   @GET
   @Path("/audits")
   @Produces(MediaType.APPLICATION_JSON)
   public String getAllAudits() {
      StringBuilder str = new StringBuilder();
      //for(Audit a : auditDA.getAllAudits()){
      str.append(auditDA.getAllAudits());
      //}
      return str.toString();
   }

   @GET
   @Path("/auditparid/{auditId}")
   @Produces(MediaType.APPLICATION_JSON)
   public String getAuditparId(@PathParam("auditId") int auditId) {
      StringBuilder result = new StringBuilder();
      try(AuditDA da = new AuditDA()) {
         result.append(da.AuditparId(auditId));
      } catch (IdNotFoundException e) {
         return e.getMessage();
      }
      catch (SQLException e) {
         return e.getMessage();
      }
      return result.toString();
   }

   @POST
   @Path("/insert")
   @Produces(MediaType.APPLICATION_JSON)
   public String InsertAudit(@FormParam("dateAudit") Date dateaudit,
                             @FormParam("dureeAudit") int dureeaudit,
                             @FormParam("coutAudit") int coutaudit,
                             @FormParam("Idindustrie") int idindustrie,
                             @FormParam("IdAuditeur") int idAuditeur) throws SQLException {
      StringBuilder result = new StringBuilder();
      Audit audit = new Audit(dateaudit, dureeaudit, coutaudit, idindustrie, idAuditeur);
      try (AuditDA da = new AuditDA()) {
         result.append(da.insertaudit(audit));
      }
      return result.toString();
   }

   @PUT
   @Path("/update")
   @Produces(MediaType.APPLICATION_JSON)
   public Response updateAuditREST(@FormParam("auditId") int auditId,
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
         return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
      }
   }

   @DELETE
   @Path("/deleaudit/{AuditId}")
   @Produces(MediaType.APPLICATION_JSON)
   public String deleteAudit(@PathParam("AuditId") int AuditId){
      StringBuilder str = new StringBuilder();
      try (AuditDA da = new AuditDA()) {
         str.append(da.DelAudit(AuditId));
      } catch (SQLException e) {
         str.append("Erreur lors de la suppression de l'audit : ");
         str.append(e.getMessage());
      }
      return str.toString();
   }

}