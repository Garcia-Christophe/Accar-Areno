package com.entities;

import javax.persistence.*;

import lombok.Data;

import java.util.List;

@Entity
@Data
public class Groupe {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idGroupe;
    private String nom;
    private int nbArtistes;
    @OneToMany
    private List<Concert> concerts;

}








