package com.entities;

import java.sql.Date;

import javax.persistence.*;

import lombok.Data;

@Entity
@Data
public class Artiste {

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
    //@Temporal(TemporalType.DATE)
    private Date dateNaissance;
    @JoinColumn(name = "idGroupe", referencedColumnName = "idGroupe")
    @ManyToOne
    private Groupe idGroupe;


}
