package com.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author christophe.garci
 */
@Entity
@Table(name = "Groupe")
@NamedQueries({ @NamedQuery(name = "Groupe.findAll", query = "SELECT g FROM Groupe g"),
        @NamedQuery(name = "Groupe.findByIdGroupe", query = "SELECT g FROM Groupe g WHERE g.idGroupe = :idGroupe"),
        @NamedQuery(name = "Groupe.findByNom", query = "SELECT g FROM Groupe g WHERE g.nom = :nom"),
        @NamedQuery(name = "Groupe.findByNbArtistes", query = "SELECT g FROM Groupe g WHERE g.nbArtistes = :nbArtistes") })
public class Groupe implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idGroupe")
    private Integer idGroupe;
    @Column(name = "nom")
    private String nom;
    @Column(name = "nbArtistes")
    private Integer nbArtistes;
    @OneToMany(mappedBy = "idGroupe")
    private Set<Concert> concertSet;
    @OneToMany(mappedBy = "idGroupe")
    private Set<Artiste> artisteSet;

    public Groupe() {
        this.nbArtistes = 0;
    }

    public Groupe(Integer idGroupe) {
        this.idGroupe = idGroupe;
    }

    public Integer getIdGroupe() {
        return idGroupe;
    }

    public void setIdGroupe(Integer idGroupe) {
        this.idGroupe = idGroupe;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Integer getNbArtistes() {
        return nbArtistes;
    }

    public void setNbArtistes(Integer nbArtists) {
        this.nbArtistes = nbArtists;
    }

    public Set<Concert> getConcertSet() {
        return concertSet;
    }

    public void setArtisteSet(Set<Artiste> artisteSet) {
        this.artisteSet = artisteSet;
    }

    public Set<Artiste> getArtisteSet() {
        return artisteSet;
    }

    public void setConcertSet(Set<Concert> concertSet) {
        this.concertSet = concertSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idGroupe != null ? idGroupe.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Groupe)) {
            return false;
        }
        Groupe other = (Groupe) object;
        if ((this.idGroupe == null && other.idGroupe != null)
                || (this.idGroupe != null && !this.idGroupe.equals(other.idGroupe))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "data.Groupe[ idGroupe=" + idGroupe + " ]";
    }

}