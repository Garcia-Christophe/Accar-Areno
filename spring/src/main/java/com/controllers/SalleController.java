package com.controllers;

import com.dtos.SalleDto;
import com.services.SalleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/salles")
@CrossOrigin(origins = "http://localhost:8081")
public class SalleController {

    private final SalleService salleService;

    public SalleController(SalleService salleService) {
        this.salleService = salleService;
    }

    /**
    Récupère toutes les salles.
    @return Liste des salles
    */
    @GetMapping
    public List<SalleDto> getSalles() {
        return salleService.getAllSalles();
    }

    /**
    Récupère une salle par son id.
    @param id Identifiant de la salle
    @return Salle correspondante
    */
    @GetMapping("/{id}")
    public SalleDto getSalle(@PathVariable int id){
        return salleService.getSalleById(id);
    }

    /**
    Enregistre une salle.
    @param salleDto Salle à enregistrer
    @return Salle enregistrée
    */
    @PostMapping
    public SalleDto saveSalle(final @RequestBody SalleDto salleDto){
        return salleService.saveSalle(salleDto);
    }

	/**
    Supprime une salle.
    @param id Identifiant de la salle à supprimer
    @return Vrai si la salle a été supprimée avec succès, faux sinon
    */
    @DeleteMapping("/{id}")
    public Boolean deleteSalle(@PathVariable int id){
        return salleService.deleteSalle(id);
    }

	/**
    Met à jour une salle.
    @param id Identifiant de la salle à mettre à jour
    @param salleDto Nouvelle salle
    @return Salle mise à jour
    */
    @PutMapping("/{id}")
    public SalleDto updateSalle(@PathVariable int id, final @RequestBody SalleDto salleDto) {
        return salleService.updateSalle(id, salleDto);
    }


}
