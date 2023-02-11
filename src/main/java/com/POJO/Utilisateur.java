package com.POJO;

public class Utilisateur {

    private static final long serialVersionUID = 1L;

    private int idUtilisateur;
    private String mdp;
    private String nom;

    public Utilisateur() {
    }

    public Utilisateur(int idUtilisateur, String mdp, String nom) {


        this.idUtilisateur = idUtilisateur;
        this.mdp = mdp;
        this.nom = nom;
    }

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
