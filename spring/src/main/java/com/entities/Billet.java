package com.entities;

import javax.persistence.*;
import lombok.Data;

@Entity
@Data
public class Billet {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idBillet;
    private Integer prix;
    @ManyToOne
    @JoinColumn(name = "idSoiree")
    private Soiree soiree;
    @ManyToOne
    @JoinColumn(name = "idUtilisateur")
    private Utilisateur utilisateur;
}