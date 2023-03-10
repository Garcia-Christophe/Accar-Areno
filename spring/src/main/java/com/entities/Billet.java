package com.entities;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author christophe.garci
 */
@Entity
@Table(name = "Billet")
@NamedQueries({ @NamedQuery(name = "Billet.findAll", query = "SELECT b FROM Billet b"),
        @NamedQuery(name = "Billet.findByIdBillet", query = "SELECT b FROM Billet b WHERE b.idBillet = :idBillet"),
        @NamedQuery(name = "Billet.findByPrix", query = "SELECT b FROM Billet b WHERE b.prix = :prix") })
public class Billet implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idBillet")
    private Integer idBillet;
    @Basic(optional = false)
    @Column(name = "prix")
    private int prix;
    @JoinColumn(name = "idSoiree", referencedColumnName = "idSoiree")
    @ManyToOne
    private Soiree idSoiree;
    @JoinColumn(name = "idUtilisateur", referencedColumnName = "idUtilisateur")
    @ManyToOne
    private Utilisateur idUtilisateur;

    public Billet() {
    }

    public Billet(Integer idBillet) {
        this.idBillet = idBillet;
    }

    public Billet(Integer idBillet, int prix) {
        this.idBillet = idBillet;
        this.prix = prix;
    }

    public Integer getIdBillet() {
        return idBillet;
    }

    public void setIdBillet(Integer idBillet) {
        this.idBillet = idBillet;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public Soiree getIdSoiree() {
        return idSoiree;
    }

    public void setIdSoiree(Soiree idSoiree) {
        this.idSoiree = idSoiree;
    }

    public Utilisateur getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(Utilisateur idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idBillet != null ? idBillet.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Billet)) {
            return false;
        }
        Billet other = (Billet) object;
        if ((this.idBillet == null && other.idBillet != null)
                || (this.idBillet != null && !this.idBillet.equals(other.idBillet))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "data.Billet[ idBillet=" + idBillet + " ]";
    }

}

