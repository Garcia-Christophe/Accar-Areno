package com.dtos;

import lombok.Data;

@Data
public class SalleDto {

    private int idSalle;
    private String nom;
    private String adresse;
    private int capacite;
    private String nomGest;
    private String prenomGest;
    private String association;
}
