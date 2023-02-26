package com.entities;

import javax.persistence.*;
import lombok.Data;

@Entity
@Data
public class Billet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idBillet")
    private Integer idBillet;
    @Basic(optional = false)
    @Column(name = "prix")
    private int prix;
    @JoinColumn(name = "idSoiree", referencedColumnName = "idSoiree")
    @ManyToOne
    private Soiree idSoiree;
    @JoinColumn(name = "idUtilisateur", referencedColumnName = "idUtilisateur")
    @ManyToOne
    private Utilisateur idUtilisateur;

}