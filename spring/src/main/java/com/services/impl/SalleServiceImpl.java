package com.services.impl;

import com.dtos.SalleDto;
import com.dtos.SoireeDto;
import com.entities.Salle;
import com.entities.Soiree;
import com.repositories.SalleRepository;
import com.repositories.SoireeRepository;
import com.services.SalleService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;
@Service("salleService")

	/**
    La classe SalleServiceImpl implémente l'interface SalleService
    et fournit une implémentation de ses méthodes.
    */
public class SalleServiceImpl implements SalleService {

    private final SalleRepository salleRepository;
    private final SoireeRepository soireeRepository;
    private final SoireeServiceImpl soireeService;


    public SalleServiceImpl(SalleRepository salleRepository, SoireeRepository soireeRepository, SoireeServiceImpl soireeService) {
        this.salleRepository = salleRepository;
        this.soireeRepository = soireeRepository;
        this.soireeService = soireeService;
    }

   /**
    *Ajoute une salle en base de données à partir d'un objet SalleDto.
    *@param salleDto l'objet SalleDto à enregistrer
    *@return l'objet SalleDto correspondant à la salle enregistrée en bdd
    */
    @Override
    public SalleDto saveSalle(SalleDto salleDto) {
        Salle salle = null;
        //Vérification que l'identifiant n'est pas déjà utilisé
        try {
            SalleDto s = this.getSalleById(salleDto.getIdSalle());
            throw new EntityExistsException("L'identifiant de la salle existe déjà");
        }catch(EntityNotFoundException e){
            //Vérification que le nom, l'adresse et la capacité sont bien fourni.
            if(salleDto.getNom()==null || salleDto.getNom().length()==0|| salleDto.getAdresse()==null|| salleDto.getAdresse().length()==0 || salleDto.getCapacite()<=0){
                throw new NoResultException("Le nom, l'addresse et la capicité doivent être indiqués.");
            }
            salle = salleRepository.save(salleDtoToEntity(salleDto));
        }
        return salleEntityToDto(salle);
    }

    /**
    *Récupère une salle par son identifiant.
    *@param salleId l'identifiant de la salle à récupérer
    *@return un objet SalleDto représentant la salle correspondante à l'identifiant donné
    */
    @Override
    public SalleDto getSalleById(int salleId) {
        Salle salle = salleRepository.findById(salleId).orElseThrow(() -> new EntityNotFoundException("Salle not found"));
        return this.salleEntityToDto(salle);
    }

    /**
    *Supprime une salle a partir de son id.
    *Si la salle a été réservée pour une ou plusieurs soirées, les soirées associées seront également supprimées.
    *@param salleId l'id de la salle à supprimer.
    *@return true si la salle a été supprimée avec succès, sinon false.
    */
    @Override
    public boolean deleteSalle(int salleId) {
        List<Soiree> soireeList = soireeRepository.findAll();
        for( Soiree soiree : soireeList)
            if(soiree.getIdSalle().getIdSalle()==salleId)
                soireeService.deleteSoiree(soiree.getIdSoiree());
        salleRepository.deleteById(salleId);
        return true;
    }

    /**
    *Récupère la liste de toutes les salles.
    *@return une liste de toutes les salles existantes.
    */
    @Override
    public List<SalleDto> getAllSalles() {
        List<SalleDto> salleDtoList = new ArrayList<>();
        List<Salle> salleList = salleRepository.findAll();
        for (Salle salle : salleList)
            salleDtoList.add(salleEntityToDto(salle));
        return salleDtoList;
    }

    /**
    Met à jour les informations d'une salle existante, par son id.
    @param salleId l'id de la salle à mettre à jour
    @param salleDto les nouvelles informations de la salle
    @return la salle mise à jour en SalleDto

     */
    @Override
    public SalleDto updateSalle(int salleId, SalleDto salleDto) {
        Salle salle = salleRepository.findById(salleId).orElseThrow(() -> new EntityNotFoundException("Salle not found"));
        SalleDto salleDto1 = null;
        if(salleDto.getNom() != null && salleDto.getNom().length()>0){
            salle.setNom(salleDto.getNom());
        }
        if(salleDto.getAdresse() != null && salleDto.getAdresse().length()>0){
            salle.setAdresse(salleDto.getAdresse());
        }
        if(salleDto.getCapacite()>0){
            salle.setCapacite(salleDto.getCapacite());
        }
        if(salleDto.getNomGest() != null && salleDto.getNomGest().length()>0){
            salle.setNomGest(salleDto.getNomGest());
        }
        if(salleDto.getPrenomGest() != null && salleDto.getPrenomGest().length()>0){
            salle.setPrenomGest(salleDto.getPrenomGest());
            System.out.println("prenom");
        }
        if(salleDto.getAssociation() != null && salleDto.getAssociation().length()>0){
            salle.setAssociation(salleDto.getAssociation());
        }
        salleRepository.save(salle);
        salleDto1 = this.salleEntityToDto(salle);
        return salleDto1;
    }
	/**
	convertit un objet de type Salle en un objet de type SalleDto.
	@param salle  la salle à convertir en SalleDto
	@return SalleDto correspondant à la salle fourni
	*/
    private SalleDto salleEntityToDto(Salle salle){
        SalleDto salleDto = new SalleDto();
        salleDto.setIdSalle(salle.getIdSalle());
        salleDto.setNom(salle.getNom());
        salleDto.setAdresse(salle.getAdresse());
        salleDto.setCapacite(salle.getCapacite());
        salleDto.setAssociation(salle.getAssociation());
        salleDto.setNomGest(salle.getNomGest());
        salleDto.setPrenomGest(salle.getPrenomGest());
        return salleDto;
    }

	/**
	convertit un objet de type Salle en un objet de type SalleDto.
	@param @param salleDto l'objet SalleDto à convertir en salle
	@return Salle correspondant à la salleDto fourni
	*/
    private Salle salleDtoToEntity(SalleDto salleDto) {
        Salle salle = new Salle();
        salle.setIdSalle(salleDto.getIdSalle());
        salle.setNom(salleDto.getNom());
        salle.setAdresse(salleDto.getAdresse());
        salle.setCapacite(salleDto.getCapacite());
        salle.setAssociation(salleDto.getAssociation());
        salle.setNomGest(salleDto.getNomGest());
        salle.setPrenomGest(salleDto.getPrenomGest());
        return salle;
    }
}
