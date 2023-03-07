package fr.m2i.securauditgroupe1.data;

import fr.m2i.securauditgroupe1.model.Categorie;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CategorieDA extends DataAccess implements AutoCloseable {

    //creation de connexion
    public CategorieDA() {
        super();
    }

    private String INSERT_Categorie_QUERY = "INSERT INTO Categorie(libelle) VALUES (?)";
    private String SELECT_Categorie_QUERY = "SELECT * FROM Categorie " ;
    private String UPDATE_Categorie_QUERY = "UPDATE Categorie SET libelle = ? WHERE idCategorie = ? ";
    private String DELETE_Categorie_QUERY = "DELETE FROM Categorie WHERE idCategorie = ?";
    private String SELECT_BY_ID_Categorie_QUERY = "SELECT * FROM Categorie WHERE idCategorie = ?";


    public String  insertionCategorie(Categorie Categorie){
        String s = "";
        int i = 0;
        try{
            PreparedStatement ps = this.getConnection().prepareStatement(this.INSERT_Categorie_QUERY, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, Categorie.getLibelle());
            ps.executeUpdate();


            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                i = rs.getInt(1);
            }
            s = " new Categorie with id " + i + " has been added ";

        } catch (SQLException e) {
            s = e.getMessage();
        }
        return s ;

    }

    public ArrayList<Categorie> selectCategorie() {

        ArrayList<Categorie> Categorie = new ArrayList<Categorie>();

        try {

            Statement stmt =  this.getConnection().createStatement();
            ResultSet resultats = stmt.executeQuery(this.SELECT_Categorie_QUERY);
            while(resultats.next()) {
                Categorie.add(new Categorie(resultats.getInt(1),resultats.getString(2)));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return Categorie;

    }

    public String deleteCategorie(int idCategorie) {
        String s= "";
        try {

            PreparedStatement ps =  this.getConnection().prepareStatement(this.DELETE_Categorie_QUERY);
            ps.setInt(1, idCategorie);
            ps.executeUpdate();
            s = " Categorie with id " + idCategorie + " has been deleted ";
        } catch (SQLException e) {
            s= e.getMessage();

        }
        return  s;
    }

    public String updateCategorie(Categorie Categorie, int idCategorie)  {
        String s = "";
        try {
            PreparedStatement ps =  this.getConnection().prepareStatement(this.UPDATE_Categorie_QUERY);
            ps.setString(1, Categorie.getLibelle());
            ps.setInt(2, idCategorie);
            ps.executeUpdate();
            s =  " Categorie with id " + idCategorie + " has been updated  ";
        } catch (SQLException e) {
            s = e.getMessage();
        }
        return s;
    }


    public Categorie selectByIdCategorie(int idCategorie) {

        Categorie Categorie = new Categorie();

        try {


            PreparedStatement ps =  this.getConnection().prepareStatement(this.SELECT_BY_ID_Categorie_QUERY);
            ps.setInt(1, idCategorie);
            ResultSet resultats = ps.executeQuery();
            if(resultats.next()) {
                Categorie = new Categorie(resultats.getInt(1),resultats.getString(2));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return Categorie;

    }

}








