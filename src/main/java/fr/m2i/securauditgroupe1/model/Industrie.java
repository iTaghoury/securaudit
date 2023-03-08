package fr.m2i.securauditgroupe1.model;

public class Industrie {
    private int idIndustrie;
    private String siret, raisonSociale;

    //region CONSTRUCTORS
    public Industrie(String siret, String raisonSociale) {
        this.siret = siret;
        this.raisonSociale = raisonSociale;
    }

    public Industrie(int idIndustrie, String siret, String raisonSociale) {
        this.idIndustrie = idIndustrie;
        this.siret = siret;
        this.raisonSociale = raisonSociale;
    }
    //endregion

    //region GETTERS AND SETTERS
    public int getIdIndustrie() {
        return idIndustrie;
    }
    public void setIdIndustrie(int idIndustrie) {
        this.idIndustrie = idIndustrie;
    }

    public String getSiret() {
        return siret;
    }

    public void setSiret(String siret) {
        this.siret = siret;
    }

    public String getRaisonSociale() {
        return raisonSociale;
    }

    public void setRaisonSociale(String raisonSociale) {
        this.raisonSociale = raisonSociale;
    }
    //endregion

}
