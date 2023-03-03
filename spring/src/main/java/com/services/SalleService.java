package com.services;

import com.dtos.SalleDto;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Interface pour la gestion des objets Salle.
 */
@Service
public interface SalleService {

    /**
     * Enregistre un nouvel objet SalleDto.
     * @param salleDto L'objet SalleDto à enregistrer.
     * @return L'objet SalleDto enregistré.
     */
    SalleDto saveSalle(SalleDto salleDto);

    /**
     * Récupère un objet SalleDto par son ID.
     * @param salleId L'ID de l'objet SalleDto à récupérer.
     * @return L'objet SalleDto récupéré.
     */
    SalleDto getSalleById(int salleId);

    /**
     * Supprime un objet SalleDto par son ID.
     * @param salleId L'ID de l'objet SalleDto à supprimer.
     * @return Vrai si l'objet SalleDto a été supprimé avec succès, faux sinon.
     */
    boolean deleteSalle(int salleId);

    /**
     * Récupère une liste de tous les objets SalleDto.
     * @return La liste des objets SalleDto.
     */
    List<SalleDto> getAllSalles();

    /**
     * Met à jour un objet SalleDto par son ID.
     * @param salleId L'ID de l'objet SalleDto à mettre à jour.
     * @param salleDto L'objet SalleDto mis à jour.
     * @return L'objet SalleDto mis à jour.
     */
    SalleDto updateSalle(int salleId, SalleDto salleDto);
}
