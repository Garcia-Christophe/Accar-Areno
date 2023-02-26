package com.entities;

import javax.persistence.*;

import lombok.Data;

import java.sql.Date;
import java.sql.Time;

@Entity
@Data
public class Concert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idConcert")
    private Integer idConcert;
    @Column(name = "date")
    //@Temporal(TemporalType.DATE)
    private Date date;
    @Column(name = "heure")
    //@Temporal(TemporalType.TIME)
    private Time heure;
    @Column(name = "duree")
    //@Temporal(TemporalType.TIME)
    private Time duree;
    @JoinColumn(name = "idGroupe", referencedColumnName = "idGroupe")
    @ManyToOne
    private Groupe idGroupe;
    @JoinColumn(name = "idSoiree", referencedColumnName = "idSoiree")
    @ManyToOne
    private Soiree idSoiree;



}
