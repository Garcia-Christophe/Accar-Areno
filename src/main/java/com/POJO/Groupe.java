package com.POJO;

public class Groupe {

    private static final long serialVersionUID = 1L;

    private int idGroupe;
    private String nom;
    private int nbArtists;
    private int idArtiste;

    public Groupe(int idGroupe, String nom, int nbArtists, int idArtiste) {
        this.idGroupe = idGroupe;
        this.nom = nom;
        this.nbArtists = nbArtists;
        this.idArtiste = idArtiste;
    }

    public Groupe() {
    }

    public int getIdGroupe() {
        return idGroupe;
    }

    public void setIdGroupe(int idGroupe) {
        this.idGroupe = idGroupe;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getNbArtists() {
        return nbArtists;
    }

    public void setNbArtists(int nbArtists) {
        this.nbArtists = nbArtists;
    }

    public int getIdArtiste() {
        return idArtiste;
    }

    public void setIdArtiste(int idArtiste) {
        this.idArtiste = idArtiste;
    }
}
