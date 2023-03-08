package fr.m2i.securauditgroupe1.data;

import fr.m2i.securauditgroupe1.exception.IdNotFoundException;
import fr.m2i.securauditgroupe1.model.Audit;

import java.sql.*;
import java.util.ArrayList;

public class AuditDA extends DataAccess {

    private static final String SELECT_AUDIT = "SELECT * FROM Audit";
    private static final String SELECT_AUDIT_parId = "SELECT * FROM Audit WHERE idAudit = ?";
    private static final String INSERT_AUDIT = "INSERT INTO Audit (dateAudit,dureeAudit,coutJour,idIndustrie,idAuditeur) VALUE(?,?,?,?,?)";
    private static final String UPDATE_AUDIT = "UPDATE Audit SET dateAudit = ? , dureeAudit = ?, coutJour = ?, idIndustrie = ?,idAuditeur = ? WHERE idAudit = ?";
    private static final String DELETE_AUDIT_BYID = "DELETE FROM audit where idAudit = ?";
    private final String CHECK_FOR_FRAIS_QUERY = "SELECT * FROM Audit INNER JOIN Frais ON Audit.idAudit = Frais.idAudit WHERE Audit.idAudit = ?";

    public AuditDA() throws SQLException {}

    public ArrayList<Audit> getAllAudits() throws SQLException {
        ArrayList<Audit> audits = new ArrayList<>();
        try (PreparedStatement stmt = this.getConnection().prepareStatement(SELECT_AUDIT);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Audit audit = new Audit(rs.getInt("idAudit"),
                                        rs.getDate("dateAudit"),
                                        rs.getInt("dureeAudit"),
                                        rs.getInt("coutJour"),
                                        rs.getInt("idIndustrie"),
                                        rs.getInt("idAuditeur"));
                audits.add(audit);
            }
            return audits;
        }
    }


    public Audit getAuditById(int userId) throws IdNotFoundException, SQLException {
        try (PreparedStatement prs = this.getConnection().prepareStatement(SELECT_AUDIT_parId)) {
            prs.setInt(1, userId);
            ResultSet rs = prs.executeQuery();
            if (rs.next()) {
                Audit audit = new Audit(rs.getInt("idAudit"),
                                        rs.getDate("dateAudit"),
                                        rs.getInt("dureeAudit"),
                                        rs.getInt("coutJour"),
                                        rs.getInt("idIndustrie"),
                                        rs.getInt("idAuditeur"));
                return audit;
            } else  {
                    throw new IdNotFoundException("Audit Id not found");
            }
        }
    }
    public String insertAudit(Audit audit) throws SQLException {
        try(PreparedStatement prs = this.getConnection().prepareStatement(INSERT_AUDIT, Statement.RETURN_GENERATED_KEYS)){
            prs.setDate(1, audit.getDateAudit());
            prs.setInt(2, audit.getDureeAudit());
            prs.setInt(3, audit.getCoutJour());
            prs.setInt(4, audit.getIdIndustrie());
            prs.setInt(5, audit.getIdAuditeur());
            prs.executeUpdate();
            try(ResultSet rs = prs.getGeneratedKeys()) {
                if(rs.next()) {
                    return String.format("Ajout de l'audit réussi, id = %d", rs.getInt(1));
                }
            }
        }
        return "Échec de l'ajout";
    }
    public void updateAudit(Audit audit) throws IdNotFoundException, SQLException {
            try(PreparedStatement prs = this.getConnection().prepareStatement(UPDATE_AUDIT)){
                this.getAuditById(audit.getIdAudit());
                prs.setDate(1, audit.getDateAudit());
                prs.setInt(2, audit.getDureeAudit());
                prs.setInt(3, audit.getCoutJour());
                prs.setInt(4, audit.getIdIndustrie());
                prs.setInt(5, audit.getIdAuditeur());
                prs.setInt(6, audit.getIdAudit());
                prs.executeUpdate();
        }
    }

    public String deleteAuditById(int auditId) throws IdNotFoundException, SQLException {
        if(isUsedAsFK(auditId)) {
            throw new SQLException("Cannot delete Audit because of FK constraint");
        } else {
            Audit foundAudit = this.getAuditById(auditId);
            try (PreparedStatement prs = this.getConnection().prepareStatement(DELETE_AUDIT_BYID)) {
                prs.setInt(1, auditId);
                prs.executeUpdate();
                return String.format("Deleted Audit with id %d", foundAudit.getIdAudit());
            }
        }
    }

    //region OTHER METHODS

    public boolean isUsedAsFK(int id) throws SQLException{
        try(PreparedStatement ps = this.getConnection().prepareStatement(CHECK_FOR_FRAIS_QUERY)) {
            ps.setInt(1, id);
            try(ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }

    //endregion

}

