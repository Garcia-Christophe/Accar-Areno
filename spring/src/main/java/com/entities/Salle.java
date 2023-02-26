package com.entities;

import javax.persistence.*;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class Salle {
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







}
