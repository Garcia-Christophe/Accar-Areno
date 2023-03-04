package com.controllers;

import com.dtos.ConcertDto;
import com.services.ConcertService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/concerts")
@CrossOrigin(origins = "http://localhost:8081")
public class ConcertController {

    private final ConcertService concertService;

    public ConcertController(ConcertService concertService) {
        this.concertService = concertService;
    }


    /**
    Récupère la liste de tous les concerts.
    @return La liste de tous les concerts.
    */
    @GetMapping
    public List<ConcertDto> getConcert() {
        return concertService.getAllConcerts();
    }

    /**
    Récupère un concert par son identifiant.
    @param id L'identifiant du concert à récupérer.
    @return Le concert correspondant à l'identifiant donné.
    */
    @GetMapping("/{id}")
    public ConcertDto getConcert(@PathVariable int id){
        return concertService.getConcertById(id);
    }

	/**
    Enregistre un nouveau concert.
    @param concertDto Le concert à enregistrer.
    @return Le concert enregistré.
    */
    @PostMapping
    public ConcertDto saveConcert(final @RequestBody ConcertDto concertDto){
        return concertService.saveConcert(concertDto);
    }

	/**
    Supprime un concert par son identifiant.
    @param id L'identifiant du concert à supprimer.
    @return Un booléen indiquant si le concert a été supprimé avec succès.
    */
    @DeleteMapping("/{id}")
    public Boolean deleteConcert(@PathVariable int id){
        return concertService.deleteConcert(id);
    }


	/**
    Met à jour un concert par son identifiant.
    @param id L'identifiant du concert à mettre à jour.
    @param concertDto Les nouvelles informations du concert.
    @return Le concert mis à jour.
    */
    @PutMapping("/{id}")
    public ConcertDto updateConcert(@PathVariable int id, final @RequestBody ConcertDto concertDto){
        return concertService.updateConcert(id, concertDto);
    }

}
