package com.pojo.mysql;

import java.sql.Date;

public class Artiste {
    private int idArtiste;
    private String nom;
    private String villeOrigine;
    private Date dateNaissance;
    private int idGroupe;

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

    public int getIdGroupe() {
        return idGroupe;
    }

    public void setIdGroupe(int idGroupe) {
        this.idGroupe = idGroupe;
    }

    @Override
    public String toString() {
        return "Artiste{" +
                "idArtiste=" + idArtiste +
                ", nom='" + nom + '\'' +
                ", villeOrigine='" + villeOrigine + '\'' +
                ", dateNaissance=" + dateNaissance +
                ", idGroupe=" + idGroupe +
                '}';
    }

    public Artiste(int idArtiste, String nom, String villeOrigine, Date dateNaissance, int idGroupe) {
        this.idArtiste = idArtiste;
        this.nom = nom;
        this.villeOrigine = villeOrigine;
        this.dateNaissance = dateNaissance;
        this.idGroupe = idGroupe;
    }

    public Artiste() {
    }
}

