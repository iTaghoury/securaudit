package fr.m2i.securauditgroupe1.data;

import fr.m2i.securauditgroupe1.model.Auditeur;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuditeurDA extends DataAccess implements AutoCloseable {

    //region QUERY STRINGS

    private final String INSERT_AUDITEUR_QUERY = "INSERT INTO Auditeur (civilite, nom, prenom) VALUE (?, ?, ?)";
    private final String SELECT_AUDITEUR_QUERY = "SELECT * FROM Auditeur";

    //endregion

    public AuditeurDA() {
        super();
    }


    //region CREATE QUERY

    public void addAuditeurToDB(Auditeur auditeur) throws SQLException {
        try(PreparedStatement ps = this.getConnection().prepareStatement(INSERT_AUDITEUR_QUERY))  {
            ps.setString(1, auditeur.getCivilite());
            ps.setString(2, auditeur.getNom());
            ps.setString(3, auditeur.getPrenom());
            ps.execute();
        }
    }

    //endregion

    //region READ QUERIES

    public StringBuilder getAuditeurFromDB() throws SQLException {
        StringBuilder sb = new StringBuilder();
        try(PreparedStatement ps = this.getConnection().prepareStatement(SELECT_AUDITEUR_QUERY);
            ResultSet rs = ps.executeQuery()) {
            if(rs.next()) {
                do {
                    sb.append(String.format("Nom %s, Prenom %s\n", rs.getString(2), rs.getString(3)));
                } while(rs.next());
                return sb;
            } else {
                return null;
            }
        }
    }

    //endregion

}
