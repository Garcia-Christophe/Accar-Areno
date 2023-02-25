package com.services;

import com.dtos.UtilisateurDto;

import java.util.List;

public interface UtilisateurService {
    /**
     * Sauve a utilisateur
     */
    UtilisateurDto saveUtilisateur(UtilisateurDto utilisateurDto);

    /**
     * Get a utilisateur by it's id
     */
    UtilisateurDto getUtilisateurById(int utililisateurId);

    /**
     * Delete a utilisateur by it's id
     */
    boolean deleteUtilisateur(int utililisateurId);

    /**
     * Get all the utilisateurs
     */
    List<UtilisateurDto> getAllUtilisateurs();


}
