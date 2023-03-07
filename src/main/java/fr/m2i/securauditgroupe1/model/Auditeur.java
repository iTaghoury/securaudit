package fr.m2i.securauditgroupe1.model;

public class Auditeur {

    private int id;
    private String civilite, nom, prenom;

    //region CONSTRUCTORS
    public Auditeur() {}
    public Auditeur(String civilite, String nom, String prenom) {
        this.civilite= civilite;
        this.nom = nom;
        this.prenom = prenom;
    }
    public Auditeur(int id, String civilite, String nom, String prenom) {
        this.civilite= civilite;
        this.nom = nom;
        this.prenom = prenom;
        this.id = id;
    }

    //endregion

    //region GETTERS AND SETTERS

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCivilite() {
        return civilite;
    }

    public void setCivilite(String civilite) {
        this.civilite = civilite;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    //endregion

}
