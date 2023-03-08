package fr.m2i.securauditgroupe1.data;

import fr.m2i.securauditgroupe1.model.Audit;
import jakarta.ws.rs.core.Response;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AuditDA extends DataAccess {

    private static final String SELECT_AUDIT = "SELECT * FROM audit";
    private static final String SELECT_AUDIT_parId = "SELECT * FROM audit WHERE idAudit = ?";
    private static final String INSERT_AUDIT = "INSERT INTO audit (dateAudit,dureeAudit,coutJour,idIndustrie,idAuditeur)" +
            "VALUE(?,?,?,?,?)";
    private static final String UPDATE_AUDIT = "UPDATE audit SET coutJour = ? WHERE idAudit = ?";
    private static final String DELETE_AUDIT = "DELETE FROM audit where idAudit = ?";

    public AuditDA() throws SQLException {
    }

    public String getAllAudits() {

        StringBuilder result = new StringBuilder();
        try (
             PreparedStatement stmt = this.getConnection().prepareStatement(SELECT_AUDIT);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                result.append(String.format(
                        "idAudit : %s, dateAudit : %s, dureeAudit : %s, coutJour : %s, idIndustrie: %s,idAuditeur :%s\n",
                        rs.getInt("idAudit"),
                        rs.getDate("dateAudit"),
                        rs.getInt("dureeAudit"),
                        rs.getInt("coutJour"),
                        rs.getInt("idIndustrie"),
                        rs.getInt("idAuditeur"))
                );

            }
        } catch (SQLException e) {
            throw new RuntimeException("Impossible de récupérer les audit", e);
        }
        return result.toString();
    }


    public  String AuditparId(int userId) {
        StringBuilder str = new StringBuilder();
        try (Connection conn = getConnection()) {
            PreparedStatement prs = conn.prepareStatement(SELECT_AUDIT_parId);
            prs.setInt(1, userId);
            ResultSet rs = prs.executeQuery();
            while (rs.next()) {
                str.append(String.format(
                        "idAudit : %s, dateAudit : %s, dureeAudit : %s, coutJour : %s, idIndustrie: %s,idAuditeur :%s\n",
                        rs.getInt(1),
                        rs.getDate(2),
                        rs.getInt(3),
                        rs.getInt(4),
                        rs.getInt(5),
                        rs.getInt(6)
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return str.toString();
    }
    public String insertaudit(Audit audit) throws SQLException {
        try(
                Connection conn = this.getConnection()){
            PreparedStatement prs = conn.prepareStatement(INSERT_AUDIT);
            prs.setDate(1, audit.getDateAudit());
            prs.setInt(2, audit.getDureeAudit());
            prs.setInt(3, audit.getCoutJour());
            prs.setInt(4, audit.getIdIndustrie());
            prs.setInt(5, audit.getIdAuditeur());
            prs.executeUpdate();
            return Response.ok("L'Audit a été ajoutée : ").build().toString();
        }
    }
    public String updateAudit(int coutjrs, int auditId) throws SQLException {
        StringBuilder str = new StringBuilder();
        try (
                Connection conn = getConnection()
        ) {
            if (conn == null) {
                throw new SQLException("Failed to establish database connection.");
            }
            PreparedStatement prs = conn.prepareStatement(UPDATE_AUDIT);
            prs.setInt(1, coutjrs);
            prs.setInt(2, auditId);
            int result = prs.executeUpdate();
            str.append("Updated ").append(result).append(" audit with ID ");
            return Response.ok("Update OK : ").build().toString();
        } catch (SQLException e) {
            throw e;
        }
    }
    public String DelAudit(int AuditId) throws SQLException {
        StringBuilder str = new StringBuilder();
        try (Connection conn = this.getConnection();
             PreparedStatement prs = conn.prepareStatement(DELETE_AUDIT)) {
            prs.setInt(1, AuditId);
            int Dele = prs.executeUpdate();
            if (Dele == 0) {
                return "Aucunne Audit trouvé avec cet identifiant : " + AuditId;
            } else {
                return "L'audit a été supprimée avec succès";
            }
        } catch (SQLException e) {
            if (e.getMessage().contains("La contrainte de clé étrangère échoue")) {
                return "Impossible de supprimer l'entité car elle a des dépendances.";
            } else {
                throw new RuntimeException(e);
            }
        }
    }
}

