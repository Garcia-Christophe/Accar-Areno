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

    @Override
    public SalleDto getSalleById(int salleId) {
        Salle salle = salleRepository.findById(salleId).orElseThrow(() -> new EntityNotFoundException("Salle not found"));
        return this.salleEntityToDto(salle);
    }

    @Override
    public boolean deleteSalle(int salleId) {
        List<Soiree> soireeList = soireeRepository.findAll();
        //Pour chaque soirée, si sa salle correspond à salleId alors on supprime la soirée.
        for( Soiree soiree : soireeList)
            if(soiree.getIdSalle().getIdSalle()==salleId)
                soireeService.deleteSoiree(soiree.getIdSoiree());
        salleRepository.deleteById(salleId);
        return true;
    }


    @Override
    public List<SalleDto> getAllSalles() {
        List<SalleDto> salleDtoList = new ArrayList<>();
        List<Salle> salleList = salleRepository.findAll();
        //Pour chaque salle de la BDD, récupère l'équivalent en SalleDto.
        for (Salle salle : salleList)
            salleDtoList.add(salleEntityToDto(salle));
        return salleDtoList;
    }

    @Override
    public SalleDto updateSalle(int salleId, SalleDto salleDto) {
        Salle salle = salleRepository.findById(salleId).orElseThrow(() -> new EntityNotFoundException("Salle not found"));
        SalleDto salleDto1 = null;
        //Si le nom fourni est différent de null alors on modifie le nom courant par celui fourni.
        if(salleDto.getNom() != null && salleDto.getNom().length()>0){
            salle.setNom(salleDto.getNom());
        }
        //Si l'adresse fourni est différente de null alors on modifie l'adresse courante par celle fourni.
        if(salleDto.getAdresse() != null && salleDto.getAdresse().length()>0){
            salle.setAdresse(salleDto.getAdresse());
        }
        //Si la capacité fourni est différente de null alors on modifie la capacité courante par celle fourni.
        if(salleDto.getCapacite()>0){
            salle.setCapacite(salleDto.getCapacite());
        }
        //Si le nomGest fourni est différent de null alors un modifie le nomGest courant par celui fourni.
        if(salleDto.getNomGest() != null && salleDto.getNomGest().length()>0){
            salle.setNomGest(salleDto.getNomGest());
        }
        //Si le prenomGest fourni est différent de null alors un modifie le prenomGest courant par celui fourni.
        if(salleDto.getPrenomGest() != null && salleDto.getPrenomGest().length()>0){
            salle.setPrenomGest(salleDto.getPrenomGest());
            System.out.println("prenom");
        }
        //Si l'association fourni est différente de null alors on modifie l'association courante par celle fourni.
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
