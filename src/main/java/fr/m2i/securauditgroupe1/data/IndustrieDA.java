package fr.m2i.securauditgroupe1.data;

import fr.m2i.securauditgroupe1.model.Industrie;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class IndustrieDA extends DataAccess implements AutoCloseable {

    //region QUERY STRINGS
    private final String INSERT_INDUSTRIE_QUERY = "INSERT INTO Industrie (siret, raisonSociale) VALUE (?, ?)";
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
    //endregion

    //region UPDATE INDUSTRIE
    //endregion

    //region DELETE INDUSTRIE
    //endregion

    //region OTHER METHODS
    //endregion
}
