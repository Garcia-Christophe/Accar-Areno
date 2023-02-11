package com.POJO;

import java.sql.Date;

public class Artiste {

    private static final long serialVersionUID = 1L;

    private int idArtiste;
    private String nom;
    private String villeOrigine;
    private Date dateNaissance;

    public Artiste(int idArtiste, String nom, String villeOrigine, Date dateNaissance) {
        this.idArtiste = idArtiste;
        this.nom = nom;
        this.villeOrigine = villeOrigine;
        this.dateNaissance = dateNaissance;
    }

    public Artiste() {
    }

    public int getIdArtiste() {
        return idArtiste;
    }

    public void setIdArtiste(int idArtiste) {
        this.idArtiste = idArtiste;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getVilleOrigine() {
        return villeOrigine;
    }

    public void setVilleOrigine(String villeOrigine) {
        this.villeOrigine = villeOrigine;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }
}
