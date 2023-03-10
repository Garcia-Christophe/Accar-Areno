package com.entities;

import java.io.Serializable;
import java.util.HashSet;
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
@Table(name = "Salle")
@NamedQueries({ @NamedQuery(name = "Salle.findAll", query = "SELECT s FROM Salle s"),
        @NamedQuery(name = "Salle.findByIdSalle", query = "SELECT s FROM Salle s WHERE s.idSalle = :idSalle"),
        @NamedQuery(name = "Salle.findByNom", query = "SELECT s FROM Salle s WHERE s.nom = :nom"),
        @NamedQuery(name = "Salle.findByAdresse", query = "SELECT s FROM Salle s WHERE s.adresse = :adresse"),
        @NamedQuery(name = "Salle.findByCapacite", query = "SELECT s FROM Salle s WHERE s.capacite = :capacite"),
        @NamedQuery(name = "Salle.findByNomGest", query = "SELECT s FROM Salle s WHERE s.nomGest = :nomGest"),
        @NamedQuery(name = "Salle.findByPrenomGest", query = "SELECT s FROM Salle s WHERE s.prenomGest = :prenomGest"),
        @NamedQuery(name = "Salle.findByAssociation", query = "SELECT s FROM Salle s WHERE s.association = :association") })
public class Salle implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idSalle")
    private Integer idSalle;
    @Column(name = "nom")
    private String nom;
    @Column(name = "adresse")
    private String adresse;
    @Column(name = "capacite")
    private Integer capacite;
    @Column(name = "nomGest")
    private String nomGest;
    @Column(name = "prenomGest")
    private String prenomGest;
    @Column(name = "association")
    private String association;
    @OneToMany(mappedBy = "idSalle")
    private Set<Soiree> soireeSet;

    public Salle() {
        soireeSet = new HashSet<Soiree>();
    }

    public Integer getIdSalle() {
        return idSalle;
    }

    public void setIdSalle(Integer idSalle) {
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

    public Integer getCapacite() {
        return capacite;
    }

    public void setCapacite(Integer capacite) {
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

    public Set<Soiree> getSoireeSet() {
        return soireeSet;
    }

    public void setSoireeSet(Set<Soiree> soireeSet) {
        this.soireeSet = soireeSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSalle != null ? idSalle.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Salle)) {
            return false;
        }
        Salle other = (Salle) object;
        if ((this.idSalle == null && other.idSalle != null)
                || (this.idSalle != null && !this.idSalle.equals(other.idSalle))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "data.Salle[ idSalle=" + idSalle + " ]";
    }

}
