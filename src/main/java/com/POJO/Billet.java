package com.POJO;

public class Billet {

    private static final long serialVersionUID = 1L;

    private int idBillet;
    private int prix;
    private int idSoiree;
    private int idUtilisateur;

    public Billet(int idBillet, int prix, int idSoiree, int idUtilisateur) {
        this.idBillet = idBillet;
        this.prix = prix;
        this.idSoiree = idSoiree;
        this.idUtilisateur = idUtilisateur;
    }

    public Billet() {
    }

    public int getIdBillet() {
        return idBillet;
    }

    public void setIdBillet(int idBillet) {
        this.idBillet = idBillet;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public int getIdSoiree() {
        return idSoiree;
    }

    public void setIdSoiree(int idSoiree) {
        this.idSoiree = idSoiree;
    }

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }


}
