/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pojo.mysql;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author christophe.garci
 */
@Entity
@Table(name = "Artiste")
@NamedQueries({
    @NamedQuery(name = "Artiste.findAll", query = "SELECT a FROM Artiste a"),
    @NamedQuery(name = "Artiste.findByIdArtiste", query = "SELECT a FROM Artiste a WHERE a.idArtiste = :idArtiste"),
    @NamedQuery(name = "Artiste.findByNom", query = "SELECT a FROM Artiste a WHERE a.nom = :nom"),
    @NamedQuery(name = "Artiste.findByVilleOrigine", query = "SELECT a FROM Artiste a WHERE a.villeOrigine = :villeOrigine"),
    @NamedQuery(name = "Artiste.findByDateNaissance", query = "SELECT a FROM Artiste a WHERE a.dateNaissance = :dateNaissance"),
    @NamedQuery(name = "Artiste.findByIdGroupe", query = "SELECT a FROM Artiste a WHERE a.idGroupe = :idGroupe")})
public class Artiste implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idArtiste")
    private Integer idArtiste;
    @Column(name = "nom")
    private String nom;
    @Column(name = "villeOrigine")
    private String villeOrigine;
    @Column(name = "dateNaissance")
    @Temporal(TemporalType.DATE)
    private Date dateNaissance;
    @JoinColumn(name = "idGroupe", referencedColumnName = "idGroupe")
    @ManyToOne
    private Groupe idGroupe;

    public Artiste() {
    }

    public Artiste(Integer idArtiste) {
        this.idArtiste = idArtiste;
    }

    public Integer getIdArtiste() {
        return idArtiste;
    }

    public void setIdArtiste(Integer idArtiste) {
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

    public Groupe getIdGroupe() {
        return idGroupe;
    }

    public void setIdGroupe(Groupe idGroupe) {
        this.idGroupe = idGroupe;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idArtiste != null ? idArtiste.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Artiste)) {
            return false;
        }
        Artiste other = (Artiste) object;
        if ((this.idArtiste == null && other.idArtiste != null) || (this.idArtiste != null && !this.idArtiste.equals(other.idArtiste))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "data.Artiste[ idArtiste=" + idArtiste + " ]";
    }
    
}
