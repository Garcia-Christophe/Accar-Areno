package com.controllers;

import com.dtos.SalleDto;
import com.dtos.SoireeDto;
import com.services.SoireeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/soirees")
@CrossOrigin(origins = "http://localhost:8081")
public class SoireeController {

    private final SoireeService soireeService;

    public SoireeController(SoireeService soireeService) {
        this.soireeService = soireeService;
    }

   /**
    Récupère toutes les soirées.
    @return Une liste de SoireeDto contenant toutes les soirées.
    */
    @GetMapping
    public List<SoireeDto> getSoirees() {
        return soireeService.getAllSoiree();
    }

    /**
    Récupère les informations d'une soirée en fonction de son id.
    @param id l'id de la soirée à récupérer.
    @return un objet SoireeDto.
    */
    @GetMapping("/{id}")
    public SoireeDto getSoiree(@PathVariable int id){
        return soireeService.getSoireeById(id);
    }

    /**
    Ajoute une nouvelle soirée à la bdd.
    @param soireeDto SoireeDto à ajouter.
    @return SoireeDto ajoutée à la bdd.
    */
    @PostMapping
    public SoireeDto saveSoiree(final @RequestBody SoireeDto soireeDto){
        return soireeService.saveSoiree(soireeDto);
    }

    /**
    Supprime une soirée à partir de son id.
    @param id l'id de la soirée à supprimer.
    @return true si la suppression a été effectuée avec succès, false sinon.
    */
    @DeleteMapping("/{id}")
    public Boolean deleteSoiree(@PathVariable int id){
        return soireeService.deleteSoiree(id);
    }

	/**
	 * Met à jour une soirée existante en utilisant SoireeDto en entrée.
	 * @param id l'id de la soirée à mettre à jour
	 * @param soireeDto SoireeDto contenant les nouvelles valeurs
	 * @return SoireeDto mis à jour
	 */
    @PutMapping("/{id}")
    public SoireeDto updateSoiree(@PathVariable int id, final @RequestBody SoireeDto soireeDto) {
        return soireeService.updateSoiree(id, soireeDto);
    }


}
