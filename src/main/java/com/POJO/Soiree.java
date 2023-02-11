package com.POJO;

public class Soiree {

    private static final long serialVersionUID = 1L;

    private int idSoiree;
    private String nom;
    private int idSalle;
    private int idConcert;

    public Soiree(int idSoiree, String nom, int idSalle, int idConcert) {
        this.idSoiree = idSoiree;
        this.nom = nom;
        this.idSalle = idSalle;
        this.idConcert = idConcert;
    }

    public Soiree() {
    }

    public int getIdSoiree() {
        return idSoiree;
    }

    public void setIdSoiree(int idSoiree) {
        this.idSoiree = idSoiree;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getIdSalle() {
        return idSalle;
    }

    public void setIdSalle(int idSalle) {
        this.idSalle = idSalle;
    }

    public int getIdConcert() {
        return idConcert;
    }

    public void setIdConcert(int idConcert) {
        this.idConcert = idConcert;
    }
}
