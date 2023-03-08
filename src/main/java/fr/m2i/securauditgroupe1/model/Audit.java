package fr.m2i.securauditgroupe1.model;

import java.sql.Date;

public class Audit {
    private int idAudit;
    private Date dateAudit;
    private int dureeAudit;
    private int coutJour;
    private int idIndustrie;
    private int idAuditeur;

    public Audit(int idAudit, Date dateAudit, int dureeAudit, int coutJour, int idIndustrie, int IdAuditeur) {
        this.idAudit = idAudit;
        this.dateAudit = dateAudit;
        this.dureeAudit = dureeAudit;
        this.coutJour = coutJour;
        this.idIndustrie = idIndustrie;
        this.idAuditeur = IdAuditeur;
    }
    public Audit( Date dateAudit, int dureeAudit, int coutJour, int idIndustrie, int IdAuditeur) {
        this.dateAudit = dateAudit;
        this.dureeAudit = dureeAudit;
        this.coutJour = coutJour;
        this.idIndustrie = idIndustrie;
        this.idAuditeur = IdAuditeur;
    }

    public Audit() {}

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
        return idAuditeur;
    }

    public void setIdAuditeur(int idAuditeur) {
        idAuditeur = idAuditeur;
    }
}
