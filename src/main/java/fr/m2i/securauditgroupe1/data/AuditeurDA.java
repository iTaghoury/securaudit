package fr.m2i.securauditgroupe1.data;

import fr.m2i.securauditgroupe1.exception.IdNotFoundException;
import fr.m2i.securauditgroupe1.model.Audit;
import fr.m2i.securauditgroupe1.model.Auditeur;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AuditeurDA extends DataAccess implements AutoCloseable {

    //region QUERY STRINGS

    private final String INSERT_AUDITEUR_QUERY = "INSERT INTO Auditeur (civilite, nom, prenom) VALUE (?, ?, ?)";
    private final String SELECT_AUDITEUR_QUERY = "SELECT * FROM Auditeur";
    private final String SELECT_AUDITEUR_BY_ID = "SELECT * FROM Auditeur WHERE idAuditeur = ?";
    private final String UPDATE_AUDITEUR_QUERY = "UPDATE Auditeur SET civilite = ?, nom = ?, prenom = ? WHERE idAuditeur = ?";

    //endregion

    public AuditeurDA() {
        super();
    }


    //region CREATE QUERY

    public void addAuditeurToDB(Auditeur auditeur) throws SQLException {
        if(!(auditeur.getCivilite().equals("M.") || auditeur.getCivilite().equals("Mme"))) {
            throw new SQLException("Valeur de civilité invalide");
        } else {
            try(PreparedStatement ps = this.getConnection().prepareStatement(INSERT_AUDITEUR_QUERY))  {
                ps.setString(1, auditeur.getCivilite());
                ps.setString(2, auditeur.getNom());
                ps.setString(3, auditeur.getPrenom());
                ps.execute();
            }
        }
    }

    //endregion

    //region READ QUERIES

    public ArrayList<Auditeur> getAuditeurFromDB() throws SQLException {
        ArrayList<Auditeur> auditeurs = new ArrayList<>();
        try(PreparedStatement ps = this.getConnection().prepareStatement(SELECT_AUDITEUR_QUERY);
            ResultSet rs = ps.executeQuery()) {
            if(rs.next()) {
                do {
                    Auditeur auditeur = new Auditeur(rs.getInt("idAuditeur"), rs.getString("civilite"), rs.getString("nom"), rs.getString("prenom"));
                    auditeurs.add(auditeur);
                } while(rs.next());
                return auditeurs;
            } else {
                throw new SQLException("Empty Auditeur table");
            }
        }
    }

    public Auditeur getAuditeurById(int id) throws IdNotFoundException, SQLException {
        try(PreparedStatement ps = this.getConnection().prepareStatement(SELECT_AUDITEUR_BY_ID)) {
            ps.setInt(1, id);
            try(ResultSet rs = ps.executeQuery()) {
                if(rs.next()) {
                    Auditeur auditeur = new Auditeur(rs.getInt("idAuditeur"), rs.getString("civilite"), rs.getString("nom"), rs.getString("prenom"));
                    return auditeur;
                } else {
                    throw new IdNotFoundException("Auditeur Id not found.");
                }
            }
        }
    }


    //endregion

    //region UPDATE QUERY

    public String updateAuditeurById(Auditeur auditeur) throws SQLException {
        if(!(auditeur.getCivilite().equals("M.") || auditeur.getCivilite().equals("Mme"))) {
            throw new SQLException("Valeur de civilité invalide");
        } else {
            try (PreparedStatement ps = this.getConnection().prepareStatement(UPDATE_AUDITEUR_QUERY)) {
                Auditeur foundAuditeur = this.getAuditeurById(auditeur.getId());
                ps.setString(1, auditeur.getCivilite());
                ps.setString(2, auditeur.getNom());
                ps.setString(3, auditeur.getPrenom());
                ps.setInt(4, auditeur.getId());
                ps.execute();
                return String.format("Updated Auditeur with id %d", foundAuditeur.getId());
            } catch (IdNotFoundException e) {
                return e.getMessage();
            }
        }
    }

    //endregion

}
