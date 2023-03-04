package com.services.impl;

import com.dtos.SalleDto;
import com.entities.Salle;
import com.entities.Soiree;
import com.repositories.SalleRepository;
import com.repositories.SoireeRepository;
import com.services.SalleService;
import org.springframework.stereotype.Service;

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
        Salle salle = salleRepository.save(SalleDtoToEntity(salleDto));
        return SalleEntityToDto(salle);
    }

    /**
    *Récupère une salle par son identifiant.
    *@param salleId l'identifiant de la salle à récupérer
    *@return un objet SalleDto représentant la salle correspondante à l'identifiant donné
    */
    @Override
    public SalleDto getSalleById(int salleId) {
        return SalleEntityToDto(salleRepository.getById(salleId));
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
            salleDtoList.add(SalleEntityToDto(salle));
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
        SalleDto salleDto1 = getSalleById(salleId);
        salleDto1.setNom(salleDto.getNom());
        salleDto1.setAdresse(salleDto.getAdresse());
        salleDto1.setCapacite(salleDto.getCapacite());
        salleDto1.setNomGest(salleDto.getNomGest());
        salleDto1.setPrenomGest(salleDto.getPrenomGest());
        salleDto1.setAssociation(salleDto.getAssociation());
        return saveSalle(salleDto1);
    }
	/**
	convertit un objet de type Salle en un objet de type SalleDto.
	@param salle  la salle à convertir en SalleDto
	@return SalleDto correspondant à la salle fourni
	*/
    private SalleDto SalleEntityToDto(Salle salle){
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
    private Salle SalleDtoToEntity(SalleDto salleDto) {
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
