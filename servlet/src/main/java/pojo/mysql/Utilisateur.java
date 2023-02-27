/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pojo.mysql;

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
@Table(name = "Utilisateur")
@NamedQueries({ @NamedQuery(name = "Utilisateur.findAll", query = "SELECT u FROM Utilisateur u"),
		@NamedQuery(name = "Utilisateur.findByIdUtilisateur", query = "SELECT u FROM Utilisateur u WHERE u.idUtilisateur = :idUtilisateur"),
		@NamedQuery(name = "Utilisateur.findByMdp", query = "SELECT u FROM Utilisateur u WHERE u.mdp = :mdp"),
		@NamedQuery(name = "Utilisateur.findByNom", query = "SELECT u FROM Utilisateur u WHERE u.nom = :nom") })
public class Utilisateur implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "idUtilisateur")
	private Integer idUtilisateur;
	@Column(name = "mdp")
	private String mdp;
	@Column(name = "nom")
	private String nom;
	@OneToMany(mappedBy = "idUtilisateur")
	private Set<Billet> billetSet;

	public Utilisateur() {
		billetSet = new HashSet<Billet>();
	}

	public Integer getIdUtilisateur() {
		return idUtilisateur;
	}

	public void setIdUtilisateur(Integer idUtilisateur) {
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

	public Set<Billet> getBilletSet() {
		return billetSet;
	}

	public void setBilletSet(Set<Billet> billetSet) {
		this.billetSet = billetSet;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idUtilisateur != null ? idUtilisateur.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Utilisateur)) {
			return false;
		}
		Utilisateur other = (Utilisateur) object;
		if ((this.idUtilisateur == null && other.idUtilisateur != null)
				|| (this.idUtilisateur != null && !this.idUtilisateur.equals(other.idUtilisateur))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "data.Utilisateur[ idUtilisateur=" + idUtilisateur + " ]";
	}

}
