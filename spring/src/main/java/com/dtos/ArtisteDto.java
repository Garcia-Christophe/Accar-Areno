package com.dtos;

import lombok.Data;

import java.sql.Date;

@Data
public class ArtisteDto {
    private int idArtiste;
    private String nom;
    private String villeOrigine;
    private Date dateNaissance;
    private int idGroupe;

}
