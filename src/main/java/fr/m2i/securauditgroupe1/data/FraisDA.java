package fr.m2i.securauditgroupe1.data;
import java.sql.*;
import java.util.ArrayList;

import fr.m2i.securauditgroupe1.model.Frais;


public class FraisDA extends DataAccess  implements AutoCloseable {

    //creation de connexion
    public FraisDA() {
        super();
    }

    private String INSERT_FRAIS_QUERY = "INSERT INTO frais(dateFrais,estRembourse,montant,idAudit,idCategorie) VALUES (?,?,?,?,?)";
    private String SELECT_FRAIS_QUERY = "SELECT * FROM frais " ;
    private String UPDATE_FRAIS_QUERY = "UPDATE frais SET dateFrais= ?, estRembourse = ?,montant = ?, idAudit = ?, idCategorie = ? WHERE idFrais = ? ";
    private String DELETE_FRAIS_QUERY = "DELETE FROM frais WHERE idFrais = ?";
    private String SELECT_BY_ID_FRAIS_QUERY = "SELECT * FROM frais WHERE idFrais = ?";


    public String  insertionFrais(Frais frais){
        String s = "";
        int i = 0;
        try{
            PreparedStatement ps = this.getConnection().prepareStatement(this.INSERT_FRAIS_QUERY, Statement.RETURN_GENERATED_KEYS);
            ps.setDate(1, frais.getDateFrais());
            ps.setBoolean(2, frais.getEstRembourse());
            ps.setInt(3, frais.getMontant());
            ps.setInt(4, frais.getIdAudit());
            ps.setInt(5, frais.getIdCategorie());
            ps.executeUpdate();


            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                i = rs.getInt(1);
            }
             s = " new frais with id " + i + " has been added ";

        } catch (SQLException e) {
            s = e.getMessage();
        }
        return s ;

    }

    public ArrayList<Frais> selectFrais() {

        ArrayList<Frais> frais = new ArrayList<Frais>();

        try {

            Statement stmt =  this.getConnection().createStatement();
            ResultSet resultats = stmt.executeQuery(this.SELECT_FRAIS_QUERY);
            while(resultats.next()) {
                frais.add(new Frais(resultats.getInt(1),resultats.getDate(2), resultats.getBoolean(3), resultats.getInt(4), resultats.getInt(5), resultats.getInt(6)));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return frais;

    }

    public String deleteFrais(int idFrais) {
        String s= "";
        try {

            PreparedStatement ps =  this.getConnection().prepareStatement(this.DELETE_FRAIS_QUERY);
            ps.setInt(1, idFrais);
            ps.executeUpdate();
            s = " frais with id " + idFrais + " has been deleted ";
        } catch (SQLException e) {
             s= e.getMessage();

        }
        return  s;
    }

    public String updateFrais(Frais frais, int idFrais)  {
        String s = "";
        try {
            PreparedStatement ps =  this.getConnection().prepareStatement(this.UPDATE_FRAIS_QUERY);
            ps.setDate(1, frais.getDateFrais());
            ps.setBoolean(2, frais.getEstRembourse());
            ps.setInt(3, frais.getMontant());
            ps.setInt(4, frais.getIdAudit());
            ps.setInt(5, frais.getIdCategorie());
            ps.setInt(6, idFrais);
            ps.executeUpdate();
            s =  " frais with id " + idFrais + " has been updated  ";
        } catch (SQLException e) {
            s = e.getMessage();
        }
        return s;
    }


    public Frais selectByIdFrais(int idFrais) {

        Frais frais = new Frais();

        try {


            PreparedStatement ps =  this.getConnection().prepareStatement(this.SELECT_BY_ID_FRAIS_QUERY);
            ps.setInt(1, idFrais);
            ResultSet resultats = ps.executeQuery();
            if(resultats.next()) {
                frais = new Frais(resultats.getInt(1),resultats.getDate(2), resultats.getBoolean(3), resultats.getInt(4), resultats.getInt(5), resultats.getInt(6));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return frais;

    }

}








