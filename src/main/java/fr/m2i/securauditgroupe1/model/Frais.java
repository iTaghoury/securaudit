package fr.m2i.securauditgroupe1.model;


import java.sql.Date;

public class Frais {
    private int idFrais;
    private Date dateFrais;
    private boolean estRembourse;
    private int montant;
    private int idAudit;
    private int idCategorie;

    public int getIdFrais() {
        return idFrais;
    }

    public void setIdFrais(int idFrais) {
        this.idFrais = idFrais;
    }

    public java.sql.Date getDateFrais() {
        return dateFrais;
    }

    public void setDateFrais(Date dateFrais) {
        this.dateFrais = dateFrais;
    }

    public boolean getEstRembourse() {
        return estRembourse;
    }

    public void setEstRembourse(boolean estRembourse) {
        this.estRembourse = estRembourse;
    }

    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

    public int getIdAudit() {
        return idAudit;
    }

    public void setIdAudit(int idAudit) {
        this.idAudit = idAudit;
    }

    public int getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(int idCategorie) {
        this.idCategorie = idCategorie;
    }

    public Frais(int idFrais, Date dateFrais, boolean estRembourse, int montant, int idAudit, int idCategorie) {
        this.idFrais = idFrais;
        this.dateFrais = dateFrais;
        this.estRembourse = estRembourse;
        this.montant = montant;
        this.idAudit = idAudit;
        this.idCategorie = idCategorie;
    }

    public Frais(Date dateFrais, boolean estRembourse, int montant, int idAudit, int idCategorie) {
        this.idFrais = idFrais;
        this.dateFrais = dateFrais;
        this.estRembourse = estRembourse;
        this.montant = montant;
        this.idAudit = idAudit;
        this.idCategorie = idCategorie;
    }

    /*
    public Frais(Date dateFrais, boolean estRembourse, int montant, Audit audit, Categorie categorie) {
        this.idFrais = idFrais;
        this.dateFrais = dateFrais;
        this.estRembourse = estRembourse;
        this.montant = montant;
        this.idAudit = audit.getIdAudit();
        this.idCategorie = categorie.getIdCategorie;
    }
    */

    public Frais() {
    }

    @Override
    public String toString() {
        return "Frais{" +
                "idFrais=" + idFrais +
                ", dateFrais=" + dateFrais +
                ", estRembourse=" + estRembourse +
                ", montant=" + montant +
                ", idAudit=" + idAudit +
                ", idCategorie=" + idCategorie +
                '}';
    }

}
