/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pojo.mysql;

import java.io.Serializable;
import java.util.Date;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author christophe.garci
 */
@Entity
@Table(name = "Concert")
@NamedQueries({ @NamedQuery(name = "Concert.findAll", query = "SELECT c FROM Concert c"),
		@NamedQuery(name = "Concert.findByIdConcert", query = "SELECT c FROM Concert c WHERE c.idConcert = :idConcert"),
		@NamedQuery(name = "Concert.findByDate", query = "SELECT c FROM Concert c WHERE c.date = :date"),
		@NamedQuery(name = "Concert.findByHeure", query = "SELECT c FROM Concert c WHERE c.heure = :heure"),
		@NamedQuery(name = "Concert.findByDuree", query = "SELECT c FROM Concert c WHERE c.duree = :duree") })
public class Concert implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "idConcert")
	private Integer idConcert;
	@Column(name = "date")
	@Temporal(TemporalType.DATE)
	private Date date;
	@Column(name = "heure")
	@Temporal(TemporalType.TIME)
	private Date heure;
	@Column(name = "duree")
	@Temporal(TemporalType.TIME)
	private Date duree;
	@JoinColumn(name = "idGroupe", referencedColumnName = "idGroupe")
	@ManyToOne
	private Groupe idGroupe;
	@JoinColumn(name = "idSoiree", referencedColumnName = "idSoiree")
	@ManyToOne
	private Soiree idSoiree;

	public Concert() {
	}

	public Concert(Integer idConcert) {
		this.idConcert = idConcert;
	}

	public Integer getIdConcert() {
		return idConcert;
	}

	public void setIdConcert(Integer idConcert) {
		this.idConcert = idConcert;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getHeure() {
		return heure;
	}

	public void setHeure(Date heure) {
		this.heure = heure;
	}

	public Date getDuree() {
		return duree;
	}

	public void setDuree(Date duree) {
		this.duree = duree;
	}

	public Groupe getIdGroupe() {
		return idGroupe;
	}

	public void setIdGroupe(Groupe idGroupe) {
		this.idGroupe = idGroupe;
	}

	public Soiree getIdSoiree() {
		return idSoiree;
	}

	public void setIdSoiree(Soiree idSoiree) {
		this.idSoiree = idSoiree;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idConcert != null ? idConcert.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Concert)) {
			return false;
		}
		Concert other = (Concert) object;
		if ((this.idConcert == null && other.idConcert != null)
				|| (this.idConcert != null && !this.idConcert.equals(other.idConcert))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "data.Concert[ idConcert=" + idConcert + " ]";
	}

}
