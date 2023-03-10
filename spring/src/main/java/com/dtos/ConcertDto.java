package com.dtos;

import lombok.Data;

import java.sql.Date;
import java.sql.Time;

@Data
public class ConcertDto {
    private int idConcert;
    private Date date;
    private Time heure;
    private Time duree;
    private int idGroupe;
    private int idSoiree;


}
