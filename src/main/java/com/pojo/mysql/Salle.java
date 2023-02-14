package com.pojo.mysql;

public class Salle {
    private int idSalle;
    private String nom;
    private String adresse;
    private int capacite;
    private String nomGest;
    private String prenomGest;
    private String association;

    public int getIdSalle() {
        return idSalle;
    }

    public void setIdSalle(int idSalle) {
        this.idSalle = idSalle;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    public String getNomGest() {
        return nomGest;
    }

    public void setNomGest(String nomGest) {
        this.nomGest = nomGest;
    }

    public String getPrenomGest() {
        return prenomGest;
    }

    public void setPrenomGest(String prenomGest) {
        this.prenomGest = prenomGest;
    }

    public String getAssociation() {
        return association;
    }

    public void setAssociation(String association) {
        this.association = association;
    }

    @Override
    public String toString() {
        return "Salle{" +
                "idSalle=" + idSalle +
                ", nom='" + nom + '\'' +
                ", adresse='" + adresse + '\'' +
                ", capacite=" + capacite +
                ", nomGest='" + nomGest + '\'' +
                ", prenomGest='" + prenomGest + '\'' +
                ", association='" + association + '\'' +
                '}';
    }

    public Salle(int idSalle, String nom, String adresse, int capacite, String nomGest, String prenomGest, String association) {
        this.idSalle = idSalle;
        this.nom = nom;
        this.adresse = adresse;
        this.capacite = capacite;
        this.nomGest = nomGest;
        this.prenomGest = prenomGest;
        this.association = association;
    }

    public Salle() {
    }
}



