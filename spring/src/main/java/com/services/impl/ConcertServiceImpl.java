package com.services.impl;

import com.dtos.ConcertDto;
import com.entities.Concert;
import com.entities.Groupe;
import com.entities.Soiree;
import com.repositories.ConcertRepository;
import com.repositories.SalleRepository;
import com.repositories.SoireeRepository;
import com.services.ConcertService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("concertService")
public class ConcertServiceImpl implements ConcertService {

    private  final ConcertRepository concertRepository;
    private  final SoireeRepository soireeRepository;
    private  final SalleRepository salleRepository;

    public ConcertServiceImpl(ConcertRepository concertRepository, SoireeRepository soireeRepository, SalleRepository salleRepository) {
        this.concertRepository = concertRepository;
        this.soireeRepository = soireeRepository;
        this.salleRepository = salleRepository;
    }

    /**
    * Enregistre un nouveau concert dans la bdd.
    * @param concertDto l'objet ConcertDto à enregistrer
    * @return l'objet ConcertDto enregistré
	*/
    @Override
    public ConcertDto saveConcert(ConcertDto concertDto) {
        Concert concert = new Concert();
        List<ConcertDto> concertList = this.getAllConcerts();
        for (int i = 0 ;i< concertList.size();i++)
        {
            if (concertList.get(i).getDate().toString().equals(concertDto.getDate().toString()) && concertList.get(i).getHeure().equals(concertDto.getHeure()))
                        //if (soireeRepository.getById(concertList.get(i).getIdSoiree()).getIdSalle() == soireeRepository.getById(concertDto.getIdSoiree()).getIdSalle())
                            throw new IllegalArgumentException("vous ne pouvez pas ajouter ce concert cat il y a un autre au même jour et heure dans la salle " +
                                    soireeRepository.getById(concertList.get(i).getIdSoiree()).getIdSalle().getIdSalle());

            else if (concertList.get(i).getDate().toString().equals(concertDto.getDate().toString()) && !concertList.get(i).getHeure().equals(concertDto.getHeure()))
                if (soireeRepository.getById(concertList.get(i).getIdSoiree()).getIdSalle() != soireeRepository.getById(concertDto.getIdSoiree()).getIdSalle())
                throw new IllegalArgumentException("Le groupe "+ concertDto.getIdGroupe() +" a un concert au même jour et heure dans la salle " +
                        soireeRepository.getById(concertList.get(i).getIdSoiree()).getIdSalle().getIdSalle());
        }
        concert = concertRepository.save(ConcertDtoToEntity(concertDto));
        return ConcertEntityToDao(concert);
    }

    /**
    * Récupère le concert avec l'id donné.
    * @param concertId l'id du concert à récupérer
    * @return le concert avec l'id donné en param
    */
    @Override
    public ConcertDto getConcertById(int concertId) {
        return ConcertEntityToDao(concertRepository.getById(concertId));
    }

    /**
    * Supprime un concert de la base de données en utilisant son identifiant unique.
    * @param concertId l'id du concert à supprimer
    * @return true si le concert a été supprimé avec succès, false sinon
    */
    @Override
    public boolean deleteConcert(int concertId) {
        concertRepository.deleteById(concertId);
        return true;
    }

    /**
    * Récupère la liste de tous les concerts.
    * @return une liste de tous les concerts existants.
    */
    @Override
    public List<ConcertDto> getAllConcerts() {
        List<ConcertDto> concertDtosList = new ArrayList<>();
        List<Concert> concertList = concertRepository.findAll();
        for (Concert concert : concertList){
            concertDtosList.add(ConcertEntityToDao(concert));
        }
        return concertDtosList;
    }

    /**
    * Met à jour un concert existant avec les nouvelles informations spécifiées dans un objet ConcertDto.
    * @param concertId l'id du concert à mettre à jour.
    * @param concertDto l'objet ConcertDto contenant les nouvelles informations du concert.
    * @return l'objet ConcertDto mis à jour.
	*/
    @Override
    public ConcertDto updateConcert(int concertId, ConcertDto concertDto) {
        ConcertDto concertDto1 = getConcertById(concertId);
        concertDto1.setIdConcert(concertDto.getIdConcert());
        concertDto1.setIdGroupe(concertDto.getIdGroupe());
        concertDto1.setIdSoiree(concertDto.getIdSoiree());
        concertDto1.setDate(concertDto.getDate());
        concertDto1.setDuree(concertDto.getDuree());
        concertDto1.setHeure(concertDto.getHeure());
        return saveConcert(concertDto1);
    }


	/**
    * Convertit un objet Concert en un objet ConcertDto.
    * @param concert l'objet Concert à convertir
    * @return un objet ConcertDto correspondant à l'objet Concert converti
    */
    private ConcertDto ConcertEntityToDao(Concert concert){
        ConcertDto concertDto = new ConcertDto();
        concertDto.setIdConcert(concert.getIdConcert());
        concertDto.setDate(concert.getDate());
        concertDto.setDuree(concert.getDuree());
        concertDto.setHeure(concert.getHeure());
        concertDto.setIdSoiree(concert.getIdSoiree().getIdSoiree());
        concertDto.setIdGroupe(concert.getIdGroupe().getIdGroupe());
        return concertDto;
    }

	/**
    * Convertit un objet ConcertDto en un objet Concert.
    * @param ConcertDto l'objet ConcertDto à convertir
    * @return un objet Concert correspondant à l'objet Concert converti
    */
    private Concert ConcertDtoToEntity(ConcertDto concertDto) {
        Concert concert = new Concert();
        concert.setIdConcert(concertDto.getIdConcert());
        concert.setDate(concertDto.getDate());
        concert.setDuree(concertDto.getDuree());
        concert.setHeure(concertDto.getHeure());

        // create new SoireeEntity and set its ID
        Soiree soiree = new Soiree();
        soiree.setIdSoiree(concertDto.getIdSoiree());
        concert.setIdSoiree(soiree);

        // create new GroupeEntity and set its ID
        Groupe groupe = new Groupe();
        groupe.setIdGroupe(concertDto.getIdGroupe());
        concert.setIdGroupe(groupe);

        return concert;
    }
}
