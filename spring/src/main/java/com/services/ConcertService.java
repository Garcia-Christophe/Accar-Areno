package com.services;

import com.dtos.ConcertDto;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Interface pour la gestion des objets Concert.
 */
@Service
public interface ConcertService {

    /**
     * Enregistre un nouvel objet ConcertDto.
     * @param concertDto L'objet ConcertDto à enregistrer.
     * @return L'objet ConcertDto enregistré.
     */
    ConcertDto saveConcert(ConcertDto concertDto);

    /**
     * Récupère un objet ConcertDto par son ID.
     * @param concertId L'ID de l'objet ConcertDto à récupérer.
     * @return L'objet ConcertDto récupéré.
     */
    ConcertDto getConcertById(int concertId);

    /**
     * Supprime un objet ConcertDto par son ID.
     * @param concertId L'ID de l'objet ConcertDto à supprimer.
     * @return Vrai si l'objet ConcertDto a été supprimé avec succès, faux sinon.
     */
    boolean deleteConcert(int concertId);

    /**
     * Récupère une liste de tous les objets ConcertDto.
     * @return La liste des objets ConcertDto.
     */
    List<ConcertDto> getAllConcerts();

    /**
     * Met à jour un objet ConcertDto par son ID.
     * @param concertId L'ID de l'objet ConcertDto à mettre à jour.
     * @param concertDto L'objet ConcertDto mis à jour.
     * @return L'objet ConcertDto mis à jour.
     */
    ConcertDto updateConcert(int concertId, ConcertDto concertDto);
}
