package com.entities;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

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
    private Date date;
    @Column(name = "heure")
    private Time heure;
    @Column(name = "duree")
    private Time duree;
    @JoinColumn(name = "idGroupe", referencedColumnName = "idGroupe")
    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    private Groupe idGroupe;
    @JoinColumn(name = "idSoiree", referencedColumnName = "idSoiree")
    @ManyToOne
    private Soiree idSoiree;



}
