package com.pojo.mysql;

import java.sql.Date;
import java.sql.Time;

public class Concert {

    private static final long serialVersionUID = 1L;
    private int idConcert;
    private Date date;
    private Time heure;
    private Time duree;
    private int idGroupe;

    private int idSoiree;

    public Concert(int idConcert, Date date, Time heure, Time duree, int idGroupe, int idSoiree) {
        this.idConcert = idConcert;
        this.date = date;
        this.heure = heure;
        this.duree = duree;
        this.idGroupe = idGroupe;
        this.idSoiree= idSoiree;
    }

    public Concert() {
    }

    public int getIdConcert() {
        return idConcert;
    }

    public void setIdConcert(int idConcert) {
        this.idConcert = idConcert;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getHeure() {
        return heure;
    }

    public void setHeure(Time heure) {
        this.heure = heure;
    }

    public Time getDuree() {
        return duree;
    }

    public void setDuree(Time duree) {
        this.duree = duree;
    }

    public int getIdGroupe() {
        return idGroupe;
    }

    public void setIdGroupe(int idGroupe) {
        this.idGroupe = idGroupe;
    }

    public int getIdSoiree() {
        return idSoiree;
    }

    public void setIdSoiree(int idSoiree) {
        this.idSoiree = idSoiree;
    }

    @Override
    public String toString() {
        return "Concert{" +
                "idConcert=" + idConcert +
                ", date=" + date +
                ", heure=" + heure +
                ", duree=" + duree +
                ", idGroupe=" + idGroupe +
                ", idSoiree=" + idSoiree +
                '}';
    }
}
