package com.pojo.mysql;

public class Soiree {

    private static final long serialVersionUID = 1L;

    private int idSoiree;
    private String nom;
    private int idSalle;



    public Soiree(int idSoiree, String nom, int idSalle) {
        this.idSoiree = idSoiree;
        this.nom = nom;
        this.idSalle = idSalle;
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

    @Override
    public String toString() {
        return "Soiree{" +
                "idSoiree=" + idSoiree +
                ", nom='" + nom + '\'' +
                ", idSalle=" + idSalle +
                '}';
    }
}
