package com.entities;

import javax.persistence.*;
import lombok.Data;
import java.util.Set;

@Entity
@Data
public class Groupe {

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


}








