package com.services;

import com.dtos.SoireeDto;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Interface for managing Soiree objects.
 */
@Service
public interface SoireeService {

    /**
     * Enregistre un nouvel objet SoireeDto.
     * @param soireeDto L'objet SoireeDto à enregistrer.
     * @return L'objet SoireeDto enregistré.
     */
    SoireeDto saveSoiree(SoireeDto soireeDto);

    /**
     * Récupère un objet SoireeDto par son ID.
     * @param salleId L'ID de l'objet SoireeDto à récupérer.
     * @return L'objet SoireeDto récupéré.
     */
    SoireeDto getSoireeById(int salleId);

    /**
     * Supprime un objet SoireeDto par son ID.
     * @param salleId L'ID de l'objet SoireeDto à supprimer.
     * @return Vrai si l'objet SoireeDto a été supprimé avec succès, faux sinon.
     */
    boolean deleteSoiree(int salleId);

    /**
     * Récupère une liste de tous les objets SoireeDto.
     * @return La liste des objets SoireeDto.
     */
    List<SoireeDto> getAllSoiree();

    /**
     * Met à jour un objet SoireeDto par son ID.
     * @param soireeId L'ID de l'objet SoireeDto à mettre à jour.
     * @param soireeDto L'objet SoireeDto mis à jour.
     * @return L'objet SoireeDto mis à jour.
     */
    SoireeDto updateSoiree(int soireeId, SoireeDto soireeDto);
}
