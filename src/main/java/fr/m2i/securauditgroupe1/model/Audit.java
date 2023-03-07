package fr.m2i.securauditgroupe1.model;

import java.sql.Date;

public class Audit {
    private int idAudit;
    private static Date dateAudit;
    private static int dureeAudit;
    private static int coutJour;
    private static int idIndustrie;
    private static int IdAuditeur;

    public Audit(int idAudit, Date dateAudit, int dureeAudit, int coutJour, int idIndustrie, int IdAuditeur) {
        this.idAudit = idAudit;
        this.dateAudit = dateAudit;
        this.dureeAudit = dureeAudit;
        this.coutJour = coutJour;
        this.idIndustrie = idIndustrie;
        this.IdAuditeur = IdAuditeur;
    }
    public Audit( Date dateAudit, int dureeAudit, int coutJour, int idIndustrie, int IdAuditeur) {

        this.dateAudit = dateAudit;
        this.dureeAudit = dureeAudit;
        this.coutJour = coutJour;
        this.idIndustrie = idIndustrie;
        this.IdAuditeur = IdAuditeur;
    }

    public Audit() {
        
    }

    public int getIdAudit() {
        return idAudit;
    }

    public void setIdAudit(int idAudit) {
        this.idAudit = idAudit;
    }

    public  Date getDateAudit() {
        return dateAudit;
    }

    public void setDateAudit(Date dateAudit) {
        this.dateAudit = dateAudit;
    }

    public  int getDureeAudit() {
        return dureeAudit;
    }

    public void setDureeAudit(int dureeAudit) {
        this.dureeAudit = dureeAudit;
    }

    public  int getCoutJour() {
        return coutJour;
    }

    public void setCoutJour(int coutJour) {
        this.coutJour = coutJour;
    }

    public  int getIdIndustrie() {
        return idIndustrie;
    }

    public void setIdIndustrie(int idIndustrie) {
        this.idIndustrie = idIndustrie;
    }

    public  int getIdAuditeur() {
        return IdAuditeur;
    }

    public void setIdAuditeur(int idAuditeur) {
        IdAuditeur = idAuditeur;
    }
}
