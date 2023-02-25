package com.entities;

import java.sql.Date;

import javax.persistence.*;

import lombok.Data;

@Entity
@Data
public class Artiste {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idArtiste;
    private String nom;
    private String villeOrigine;
    private Date dateNaissance;
    @ManyToOne
    @JoinColumn(name = "idGroupe")
    private Groupe groupe;

}
