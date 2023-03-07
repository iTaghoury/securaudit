package fr.m2i.securauditgroupe1.api;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import fr.m2i.securauditgroupe1.data.AuditDA;
import fr.m2i.securauditgroupe1.model.Audit;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@Path("/execute")
public class AuditResource {

   private AuditDA auditDA = new AuditDA();

   @GET
   @Path("/audits")
   @Produces(MediaType.APPLICATION_JSON)
   public String  getAllAudits() {
      StringBuilder str = new StringBuilder();
      //for(Audit a : auditDA.getAllAudits()){
         str.append(auditDA.getAllAudits());
      //}
      return str.toString();
   }

   @GET
   @Path("/auditparid/{auditId}")
   @Produces(MediaType.APPLICATION_JSON)
   public String Todoutilisateur(@PathParam("auditId") int auditId) {
      StringBuilder result = new StringBuilder();
      result.append(auditDA.AuditparId(auditId));
      return result.toString();
   }

   @POST
   @Path("/insert")
   @Produces(MediaType.APPLICATION_JSON)
   public String InsertAudit(@FormParam("dateAudit")Date dateaudit,
                             @FormParam("dureeAudit")int dureeaudit,
                             @FormParam("coutAudit")int coutaudit,
                             @FormParam("Idindustrie")int idindustrie,
                             @FormParam("IdAuditeur")int idAuditeur) throws SQLException {
      StringBuilder result = new StringBuilder();
      Audit audit = new Audit(dateaudit,dureeaudit,coutaudit,idindustrie,idAuditeur);
      try (AuditDA da = new AuditDA()){
         result.append(da.insertaudit(audit));
      }


      return result.toString();
   }
}

