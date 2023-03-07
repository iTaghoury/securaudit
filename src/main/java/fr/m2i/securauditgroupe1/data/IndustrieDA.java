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
    //endregion

    //region DELETE INDUSTRIE
    //endregion

    //region OTHER METHODS
    //endregion
}
