package com.services.impl;

import com.dtos.SoireeDto;
import com.entities.Billet;
import com.entities.Concert;
import com.entities.Salle;
import com.entities.Soiree;
import com.repositories.ConcertRepository;
import com.repositories.SalleRepository;
import com.repositories.SoireeRepository;
import com.services.SoireeService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;

@Service("soireeService")

	/**
    La classe SoireeServiceImpl implémente l'interface SoireeService
    et fournit une implémentation de ses méthodes.
    */
public class SoireeServiceImpl implements SoireeService {

    private final SoireeRepository soireeRepository ;
    private final ConcertRepository concertRepository ;
    private final SalleRepository salleRepository;




    public SoireeServiceImpl(SoireeRepository soireeRepository, ConcertRepository concertRepository, SalleRepository salleRepository) {
        this.soireeRepository = soireeRepository;
        this.concertRepository = concertRepository;
        this.salleRepository = salleRepository;
    }

    /**
	 * Enregistre une soirée dans la base de données.
	 * @param soireeDto - L'objet SoireeDto contenant les informations de la soirée à enregistrer.
	 * @return L'objet SoireeDto sauvegardé dans la base de données.
	 */ 	
    @Override
    public SoireeDto saveSoiree(SoireeDto soireeDto) {
        SoireeDto soireeDtoReturn = null;
        //Vérification que l'identifiant de la soirée n'existe pas déjà
        try {
            SoireeDto s = this.getSoireeById(soireeDto.getIdSoiree());
            throw new EntityExistsException("L'identifiant de la soirée existe déjà");
        }catch(EntityNotFoundException e){
            //Vérification que le nom de la soirée et l'identifiant de la salle n'est pas null
            if(soireeDto.getIdSalle()==0 ||soireeDto.getNom()==null || soireeDto.getNom().length()==0 ){
                throw new NoResultException("l'identifiant de la salle et le nom de la soirée doivent être défini");
            }
            Soiree soiree = soireeRepository.save(soireeDtoToEntity(soireeDto));
            soireeDtoReturn = soireeEntityToDto(soiree);
        }
        return soireeDtoReturn;
    }

	/**
	 * Récupère une soirée de la base de données en utilisant son identifiant.
	 * @param soireeId - L'identifiant de la soirée à récupérer.
	 * @return L'objet SoireeDto correspondant à l'identifiant spécifié.
	 * @throws IllegalArgumentException si l'identifiant spécifié est invalide.
	 */
    @Override
    public SoireeDto getSoireeById(int soireeId) {
        Soiree soiree = soireeRepository.findById(soireeId).orElseThrow(() -> new EntityNotFoundException("Soiree not found"));
        return soireeEntityToDto(soiree);
    }
    /**
	 * Supprime une soirée de la base de données en utilisant son id 
	 * et supprime également tous les billets et concerts associés à cette soirée.
	 * @param soireeId - L'identifiant de la soirée à supprimer.
	 * @return true si la suppression est réussie, sinon false.
	 */
    @Override
    public boolean deleteSoiree(int soireeId) {
        Soiree soiree = soireeRepository.getById(soireeId);

        // Vérification que la soirée n'a aucun billet.
        if(soiree.getBilletSet() != null){
            if(soiree.getBilletSet().size() != 0){
                throw new NoResultException("On ne peut supprimer une soirée ayant déjà des billets.");
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
            soireeDtoList.add(soireeEntityToDto(soiree));
        return soireeDtoList;
    }
	
	/**
    Met à jour une soirée existante avec les informations contenues dans le SoireeDto correspondant.
    @param soireeId l'id de la soirée à mettre à jour.
    @param soireeDto SoireeDto contenant les informations à mettre à jour pour la soirée.
	*/
    @Override
    public SoireeDto updateSoiree(int soireeId, SoireeDto soireeDto) {
        Soiree soiree = soireeRepository.findById(soireeId).orElseThrow(() -> new EntityNotFoundException("Soiree not found"));
        SoireeDto soireeDto1 = new SoireeDto();
        //Vérifier si le nouveau nom est différent de null
        if(soireeDto.getNom() != null && soireeDto.getNom().length()>0){
            //Changement du nom par le nouveau
            soiree.setNom(soireeDto.getNom());
        }
        //Vérifier si le nouvel identifiant de salle est différent de null
        if(soireeDto.getIdSalle()!=0){
            //Changement de l'identifiant de la salle par le nouveau
            Salle salle = this.salleRepository.getById(soireeDto.getIdSalle());
            soiree.setIdSalle(salle);
        }
        soireeRepository.save(soiree);
        soireeDto1 = this.soireeEntityToDto(soiree);
        return soireeDto1;
    }

	/**
    Convertit une entité Soiree en SoireeDto.
    @param soiree l'entité Soiree à convertir en SoireeDto.
    @return un objet SoireeDto correspondant à l'entité Soiree passée en paramètre.
    */
    private SoireeDto soireeEntityToDto(Soiree soiree){
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
    private Soiree soireeDtoToEntity(SoireeDto soireeDto) {
        Soiree soiree = new Soiree();
        soiree.setNom(soireeDto.getNom());
        Salle salleEntity = new Salle();
        salleEntity.setIdSalle(soireeDto.getIdSalle());
        soiree.setIdSalle(salleEntity);
        soiree.setIdSoiree(soireeDto.getIdSoiree());
        return soiree;
    }
}
