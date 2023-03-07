package fr.m2i.securauditgroupe1.data;

import fr.m2i.securauditgroupe1.exception.IdNotFoundException;
import fr.m2i.securauditgroupe1.model.Industrie;
import fr.m2i.securauditgroupe1.exception.IdNotFoundException;

import java.sql.*;
import java.util.ArrayList;

public class IndustrieDA extends DataAccess implements AutoCloseable {

    //region QUERY STRINGS
    private final String INSERT_INDUSTRIE_QUERY = "INSERT INTO Industrie (siret, raisonSociale) VALUE (?, ?)";
    private final String SELECT_INDUSTRIE_QUERY = "SELECT * FROM Industrie";
    private final String SELECT_INDUSTRIE_BY_ID = "SELECT * FROM Industrie WHERE idIndustrie = ?";
    private final String UPDATE_INDUSTRIE_QUERY = "UPDATE industrie SET siret = ?, raisonSociale = ? WHERE idIndustrie = ?";
    private final String DELETE_INDUSTRIE_QUERY = "DELETE FROM Industrie WHERE idIndustrie = ?";
    private final String CHECK_FOR_INDUSTRIE_QUERY = "SELECT * FROM Industrie INNER JOIN Audit ON Industrie.idIndustrie = Audit.idIndustrie WHERE Industrie.idIndustrie = ?";
    //endregion

    //region CONSTRUCTOR
    public IndustrieDA() {
        super();
    }
    //endregion

    //region CREATE INDUSTRIE
    public void addIndustrie(Industrie industrie) throws SQLException {
        try(PreparedStatement ps = this.getConnection().prepareStatement(INSERT_INDUSTRIE_QUERY)) {
            ps.setString(1, industrie.getSiret());
            ps.setString(2, industrie.getRaisonSociale());
            ps.execute();
        }
    }
    //endregion

    //region READ INDUSTRIE
    public ArrayList<Industrie> getIndustrieFromDB() {
        ArrayList<Industrie> industrieList = new ArrayList<>();
        try(PreparedStatement ps = this.getConnection().prepareStatement(SELECT_INDUSTRIE_QUERY);
            ResultSet rs = ps.executeQuery())
        {
            while(rs.next()) {
                industrieList.add(new Industrie(rs.getInt("idIndustrie"),
                                                 rs.getString("siret"),
                                                 rs.getString("raisonSociale")
                ));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return industrieList;
    }

    public Industrie getIndustrieById (int idIndustrie) throws IdNotFoundException {
        try(PreparedStatement ps = this.getConnection().prepareStatement(SELECT_INDUSTRIE_BY_ID))
        {
            ps.setInt(1, idIndustrie);
            try(ResultSet rs = ps.executeQuery()) {
                if(rs.next()) {
                    Industrie industrie = new Industrie(rs.getInt("idIndustrie"), rs.getString("siret"), rs.getString("raisonSociale"));
                    return industrie;
                } else {
                    throw new IdNotFoundException("Id not found");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    //endregion

    //region UPDATE INDUSTRIE
    public void updateIndustrie(Industrie industrie) throws IdNotFoundException, SQLException {
        if(isInDB(industrie.getIdIndustrie())) {
            try (PreparedStatement ps = this.getConnection().prepareStatement(UPDATE_INDUSTRIE_QUERY)){
                ps.setString(1, industrie.getSiret());
                ps.setString(2, industrie.getRaisonSociale());
                ps.setInt(3, industrie.getIdIndustrie());
                ps.execute();
            }
        } else {
            throw new IdNotFoundException("Id not found");
        }
    }

    public void deleteIndustrie(int idIndustrie) throws IdNotFoundException, SQLException {
        if(isInDB(idIndustrie)) {
            if(isUsed(idIndustrie)) {
                throw new SQLException("Industrie used in database, cannot delete it.");
            } else {
                try(PreparedStatement ps = this.getConnection().prepareStatement(DELETE_INDUSTRIE_QUERY)) {
                    ps.setInt(1, idIndustrie);
                    ps.execute();
                }
            }
        } else {
            throw new IdNotFoundException("Id not found");
        }
    }
    //endregion

    //region DELETE INDUSTRIE
    //endregion

    //region OTHER METHODS
    public boolean isInDB(int idIndustrie) {
        try (PreparedStatement ps = this.getConnection().prepareStatement(SELECT_INDUSTRIE_BY_ID)) {
            ps.setInt(1, idIndustrie);
            try(ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean isUsed(int idIndustrie) throws SQLException {
        try(PreparedStatement ps = this.getConnection().prepareStatement(CHECK_FOR_INDUSTRIE_QUERY)) {
            ps.setInt(1, idIndustrie);
            try(ResultSet rs = ps.executeQuery()) {
                return rs.next();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                return false;
            }
        }
    }
    //endregion
}
