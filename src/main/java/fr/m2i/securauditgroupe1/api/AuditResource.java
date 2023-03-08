package fr.m2i.securauditgroupe1.api;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import fr.m2i.securauditgroupe1.data.AuditDA;
import fr.m2i.securauditgroupe1.model.Audit;
import jakarta.ws.rs.core.Response;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@Path("/audit")
public class AuditResource {

   private AuditDA auditDA = new AuditDA();

   @GET
   @Produces(MediaType.APPLICATION_JSON)
   public String getAllAudits() {
      StringBuilder str = new StringBuilder();
      //for(Audit a : auditDA.getAllAudits()){
      str.append(auditDA.getAllAudits());
      //}
      return str.toString();
   }

   @GET
   @Path("/{auditId}")
   @Produces(MediaType.APPLICATION_JSON)
   public String getAuditById(@PathParam("auditId") int auditId) {
      StringBuilder result = new StringBuilder();
      result.append(auditDA.AuditparId(auditId));
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
   @Path("/update/{auditId}/{coutjrs}")
   @Produces(MediaType.APPLICATION_JSON)
   public Response updateAuditREST(@PathParam("auditId") int auditId, @PathParam("coutjrs") int coutjrs) {
      try (AuditDA da = new AuditDA()) {
         da.updateAudit(coutjrs, auditId);
         return Response.ok().build();
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