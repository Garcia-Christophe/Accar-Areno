package com.services.impl;

import com.dtos.SoireeDto;
import com.entities.Billet;
import com.entities.Concert;
import com.entities.Salle;
import com.entities.Soiree;
import com.repositories.BilletRepository;
import com.repositories.ConcertRepository;
import com.repositories.SoireeRepository;
import com.services.SoireeService;
import org.springframework.stereotype.Service;

import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
@Service("soireeService")

	/**
    La classe SoireeServiceImpl implémente l'interface SoireeService
    et fournit une implémentation de ses méthodes.
    */
public class SoireeServiceImpl implements SoireeService {

    private final SoireeRepository soireeRepository ;
    private final BilletRepository billetRepository ;
    private final ConcertRepository concertRepository ;




    public SoireeServiceImpl(SoireeRepository soireeRepository, BilletRepository billetRepository, ConcertRepository concertRepository) {
        this.soireeRepository = soireeRepository;
        this.billetRepository = billetRepository;
        this.concertRepository = concertRepository;
    }

    /**
	 * Enregistre une soirée dans la base de données.
	 * @param soireeDto - L'objet SoireeDto contenant les informations de la soirée à enregistrer.
	 * @return L'objet SoireeDto sauvegardé dans la base de données.
	 */ 	
    @Override
    public SoireeDto saveSoiree(SoireeDto soireeDto) {
        Soiree soiree = new Soiree();
        soiree = soireeRepository.save(SoireeDtoToEntity(soireeDto));
        return SoireeEntityToDto(soiree);
    }

	/**
	 * Récupère une soirée de la base de données en utilisant son identifiant.
	 * @param soireeId - L'identifiant de la soirée à récupérer.
	 * @return L'objet SoireeDto correspondant à l'identifiant spécifié.
	 * @throws IllegalArgumentException si l'identifiant spécifié est invalide.
	 */
    @Override
    public SoireeDto getSoireeById(int soireeId) {
        Soiree soiree = soireeRepository.findById(soireeId).orElseThrow(() ->
                new IllegalArgumentException("Invalid Soiree ID: " + soireeId));
        return SoireeEntityToDto(soiree);
    }
    /**
	 * Supprime une soirée de la base de données en utilisant son id 
	 * et supprime également tous les billets et concerts associés à cette soirée.
	 * @param soireeId - L'identifiant de la soirée à supprimer.
	 * @return true si la suppression est réussie, sinon false.
	 */
    @Override
    public boolean deleteSoiree(int soireeId) {
        List<Billet> billetList = billetRepository.findAll();

        // Pour chaque billet
        for (Billet billet : billetList) {
            // S'il est relié à la soirée à supprimer
            if (billet.getIdSoiree().getIdSoiree() == soireeId) {
                // Alors on le supprime aussi
                try {
                    URL url = new URL("http://localhost:8079/accarareno/billets?id=" + billet.getIdBillet());
                    HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
                    httpCon.setDoOutput(true);
                    httpCon.setRequestProperty(
                            "Content-Type", "application/x-www-form-urlencoded");
                    httpCon.setRequestMethod("DELETE");
                    httpCon.connect();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }

        // Pour chaque concert
        List<Concert> concertList = concertRepository.findAll();
        for( Concert concert : concertList) {
            // Si le concert est prévu pour la soirée à supprimer
            if(concert.getIdSoiree().getIdSoiree() == soireeId) {
                // Alors on le supprime aussi
                concertRepository.delete(concert);
            }
        }

        // Enfin, suppression de la soirée
        soireeRepository.deleteById(soireeId);
        return true;
    }

    /**
    *Récupère la liste de toutes les soirées.
    *@return une liste de toutes les soirées existantes.
    */
    @Override
    public List<SoireeDto> getAllSoiree() {
        List<SoireeDto> soireeDtoList = new ArrayList<>();
        List<Soiree> soireeList = soireeRepository.findAll();
        for (Soiree soiree : soireeList)
            soireeDtoList.add(SoireeEntityToDto(soiree));
        return soireeDtoList;
    }
	
	/**
    Met à jour une soirée existante avec les informations contenues dans le SoireeDto correspondant.
    @param soireeId l'id de la soirée à mettre à jour.
    @param soireeDto SoireeDto contenant les informations à mettre à jour pour la soirée.
	*/
    @Override
    public SoireeDto updateSoiree(int soireeId, SoireeDto soireeDto) {
        SoireeDto soireeDto1 = new SoireeDto();
        soireeDto1.setNom(soireeDto.getNom());
        soireeDto1.setIdSoiree(soireeDto.getIdSoiree());
        soireeDto1.setIdSalle(soireeDto.getIdSalle());
        return saveSoiree(soireeDto1);
    }

	/**
    Convertit une entité Soiree en SoireeDto.
    @param soiree l'entité Soiree à convertir en SoireeDto.
    @return un objet SoireeDto correspondant à l'entité Soiree passée en paramètre.
    */
    private SoireeDto SoireeEntityToDto(Soiree soiree){
        SoireeDto soireeDto = new SoireeDto();
        soireeDto.setIdSoiree(soiree.getIdSoiree());
        soireeDto.setIdSalle(soiree.getIdSalle().getIdSalle());
        soireeDto.setNom(soiree.getNom());
        return soireeDto;
    }
	/**
    Convertit SoireeDto en entité Soiree.
    @param soireeDto l'objet SoireeDto à convertir en entité Soiree.
    @return une entité Soiree correspondant à l'objet SoireeDto passé en paramètre.
    */
    private Soiree SoireeDtoToEntity(SoireeDto soireeDto) {
        Soiree soiree = new Soiree();
        soiree.setNom(soireeDto.getNom());
        Salle salleEntity = new Salle();
        salleEntity.setIdSalle(soireeDto.getIdSalle());
        soiree.setIdSalle(salleEntity);
        soiree.setIdSoiree(soireeDto.getIdSoiree());
        return soiree;
    }
}
