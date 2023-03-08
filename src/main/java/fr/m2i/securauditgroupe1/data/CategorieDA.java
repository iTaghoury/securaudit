package fr.m2i.securauditgroupe1.data;

import fr.m2i.securauditgroupe1.exception.IdNotFoundException;
import fr.m2i.securauditgroupe1.model.Categorie;
import jakarta.ws.rs.NotFoundException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CategorieDA extends DataAccess implements AutoCloseable {

    //region CONSTRUCTOR
    public CategorieDA() {
        super();
    }

    //endregion

    //region QUERY STRINGS
    private String INSERT_Categorie_QUERY = "INSERT INTO Categorie(libelle) VALUES (?)";
    private String SELECT_Categorie_QUERY = "SELECT * FROM Categorie " ;
    private String UPDATE_Categorie_QUERY = "UPDATE Categorie SET libelle = ? WHERE idCategorie = ? ";
    private String DELETE_Categorie_QUERY = "DELETE FROM Categorie WHERE idCategorie = ?";
    private String SELECT_BY_ID_Categorie_QUERY = "SELECT * FROM Categorie WHERE idCategorie = ?";

    //endregion

    //region CREATE QUERY
    public String  insertionCategorie(Categorie Categorie) throws SQLException {
        String s = "";
        int i = 0;

            PreparedStatement ps = this.getConnection().prepareStatement(this.INSERT_Categorie_QUERY, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, Categorie.getLibelle());
            ps.executeUpdate();


            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                i = rs.getInt(1);
            }
            s = " new Categorie with id " + i + " has been added ";

        return s ;

    }

    //endregion

    //region READ QUERIES
    public ArrayList<Categorie> selectCategorie() throws SQLException  {

        ArrayList<Categorie> Categorie = new ArrayList<Categorie>();

            Statement stmt =  this.getConnection().createStatement();
            ResultSet resultats = stmt.executeQuery(this.SELECT_Categorie_QUERY);
            while(resultats.next()) {
                Categorie.add(new Categorie(resultats.getInt(1),resultats.getString(2)));
            }


        return Categorie;

    }

    public Categorie selectByIdCategorie(int idCategorie) throws SQLException, IdNotFoundException {

        Categorie Categorie = null;

        PreparedStatement ps =  this.getConnection().prepareStatement(this.SELECT_BY_ID_Categorie_QUERY);
        ps.setInt(1, idCategorie);
        ResultSet resultats = ps.executeQuery();
        if(resultats.next()) {
            Categorie = new Categorie(resultats.getInt(1),resultats.getString(2));
        }else {
            throw new IdNotFoundException(" Id Categorie not found ");
        }

        return Categorie;

    }

    //endregion

    //region UPDATE QUERY
    public String updateCategorie(Categorie Categorie, int idCategorie) throws SQLException,IdNotFoundException {

        PreparedStatement ps = this.getConnection().prepareStatement(this.UPDATE_Categorie_QUERY);
        ps.setString(1, Categorie.getLibelle());
        ps.setInt(2, idCategorie);
        if (ps.executeUpdate() != 0)
            return " Categorie with id " + idCategorie + " has been updated  ";
        else
            throw new IdNotFoundException(" Id Categorie not found ");
    }

    //endregion

    //region DELETE QUERY
    public String deleteCategorie(int idCategorie) throws SQLException, IdNotFoundException {

        PreparedStatement ps = this.getConnection().prepareStatement(this.DELETE_Categorie_QUERY);
        ps.setInt(1, idCategorie);
        if (ps.executeUpdate() != 0)
            return " Categorie with id " + idCategorie + " has been deleted ";
        else
            throw new IdNotFoundException(" Id Categorie not found ");

    }

    //endregion


}








