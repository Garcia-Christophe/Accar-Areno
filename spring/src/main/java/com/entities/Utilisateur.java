package com.entities;

import javax.persistence.*;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class Utilisateur {

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


}
