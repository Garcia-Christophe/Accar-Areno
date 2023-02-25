package com.entities;

import javax.persistence.*;

import lombok.Data;

import java.util.Set;

@Entity
@Data
public class Soiree {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idSoiree")
    private Integer idSoiree;
    @Basic(optional = false)
    @Column(name = "nom")
    private String nom;
    @JoinColumn(name = "idSalle")
    @ManyToOne
    private Salle idSalle;
    @OneToMany
    private Set<Billet> billetSet;
    @OneToMany
    private Set<Concert> concertSet;

}
