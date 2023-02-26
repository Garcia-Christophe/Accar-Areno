package com.entities;

import javax.persistence.*;

import lombok.Data;

import java.util.List;
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
    @JoinColumn(name = "idSalle", referencedColumnName = "idSalle")
    @ManyToOne
    private Salle idSalle;
    @OneToMany(mappedBy = "idSoiree")
    private Set<Billet> billetSet;
    @OneToMany(mappedBy = "idSoiree")
    private Set<Concert> concertSet;

}
