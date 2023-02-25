package com.controllers;

import com.dtos.UtilisateurDto;
import com.services.UtilisateurService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/utilisateurs")
public class UtilisateurController {

    private final UtilisateurService utilisateurService;

    public UtilisateurController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    /**
     * Get all Utilisateur in the system
     * @return List<UtilisateurDto>
     */
    @GetMapping
    public List<UtilisateurDto> getUtilisateurs() {
        return utilisateurService.getAllUtilisateurs();
    }

    /**
     * Method to get the utilisateur based on the ID
     */
    @GetMapping("/{id}")
    public UtilisateurDto getUtilisateur(@PathVariable int id){
        return utilisateurService.getUtilisateurById(id);
    }

    /**
     * Create a new utilisateur in the system
     */
    @PostMapping
    public UtilisateurDto saveUtilisateur(final @RequestBody UtilisateurDto utilisateurDto){
        return utilisateurService.saveUtilisateur(utilisateurDto);
    }

    /**
     * Delete a utilisateur by its id
     */
    @DeleteMapping("/{id}")
    public Boolean deleteUtilisateur(@PathVariable int id){
        return utilisateurService.deleteUtilisateur(id);
    }
}
