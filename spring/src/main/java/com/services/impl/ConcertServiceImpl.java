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

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
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

    @Override
    public ConcertDto saveConcert(ConcertDto concertDto) {
        Concert concert = null;
        //Vérification de l'unicité de l'identifiant du concert
        try {
            ConcertDto c =this.getConcertById(concertDto.getIdConcert());
            throw new EntityExistsException("L'identifiant du concert existe déjà.");
        }catch(EntityNotFoundException e){
            List<ConcertDto> concerts = this.getAllConcerts();
            // Pour tous les concerts existants
            for (ConcertDto c : concerts) {
                // Si le concert itéré a lieu dans la même salle
                if (soireeRepository.getById(c.getIdSoiree()).getIdSalle().getIdSalle() == soireeRepository.getById(concertDto.getIdSoiree()).getIdSalle().getIdSalle()) {
                    // Si le concert itéré a lieu le même jour
                    if (c.getDate().toString().equals(concertDto.getDate().toString())) {
                        // Si le concert itéré a lieu dans la même salle et le même jour que le concert en paramètre
                        // Et que le concert itéré débute durant le concert en paramètre
                        // Alors renvoie une erreur appropriée
                        if (c.getHeure().getTime() >= concertDto.getHeure().getTime() && c.getHeure().getTime() <= concertDto.getHeure().getTime() + concertDto.getDuree().getTime()) {
                            throw new IllegalArgumentException("Le concert ne peut débuter pendant un autre concert dans la même salle.");
                        }

                        // Si le concert itéré a lieu dans la même salle et le même jour que le concert en paramètre
                        // Et que le concert itéré se termine durant le concert en paramètre
                        // Alors renvoie une erreur appropriée
                        if (concertDto.getHeure().getTime() >= c.getHeure().getTime() && concertDto.getHeure().getTime() <= c.getHeure().getTime() + c.getDuree().getTime()) {
                            throw new IllegalArgumentException("Le concert ne peut se terminer pendant un autre concert dans la même salle.");
                        }
                    }
                }
            }

            // Si le concert peut être créé, alors l'enregistre en base de données
            concert = concertRepository.save(concertDtoToEntity(concertDto));
        }

        return concertEntityToDto(concert);
    }

    @Override
    public ConcertDto getConcertById(int concertId) {
        Concert concert = concertRepository.findById(concertId).orElseThrow(() -> new EntityNotFoundException("Concert not found"));
        return concertEntityToDto(concert);
    }

    @Override
    public boolean deleteConcert(int concertId) {
        concertRepository.deleteById(concertId);
        return true;
    }

    @Override
    public List<ConcertDto> getAllConcerts() {
        List<ConcertDto> concertDtosList = new ArrayList<>();
        List<Concert> concertList = concertRepository.findAll();
        //Pour chaque concert de la BDD, récupère l'équivalent en ConcertDto.
        for (Concert concert : concertList){
            concertDtosList.add(concertEntityToDto(concert));
        }
        return concertDtosList;
    }

    @Override
    public ConcertDto updateConcert(int concertId, ConcertDto concertDto) {
        ConcertDto concertDto1 = null;
        Concert concert = concertRepository.findById(concertId).orElseThrow(() -> new EntityNotFoundException("Concert not found"));
        //Vérification que l'id du groupe du concert ne change pas
        if(concertDto.getIdGroupe()!=concert.getIdGroupe().getIdGroupe()){
            throw new NoResultException("Impossible de changer l'identifiant du groupe.");
        }
        //Vérification que l'id de la soirée du concert ne change pas
        if(concertDto.getIdSoiree()!=concert.getIdSoiree().getIdSoiree()){
            throw new NoResultException("Impossible de changer l'identifiant de la soirée.");
        }
        List<ConcertDto> concerts = this.getAllConcerts();
        // Pour tous les concerts existants
        for (ConcertDto c : concerts) {
            // Si le concert itéré a lieu dans la même salle
            if (soireeRepository.getById(c.getIdSoiree()).getIdSalle().getIdSalle() == soireeRepository.getById(concertDto.getIdSoiree()).getIdSalle().getIdSalle()) {
                // Si le concert itéré a lieu le même jour
                if (c.getDate().toString().equals(concertDto.getDate().toString())) {
                    // Si le concert itéré a lieu dans la même salle et le même jour que le concert en paramètre
                    // Et que le concert itéré débute durant le concert en paramètre
                    // Alors renvoie une erreur appropriée
                    if (c.getHeure().getTime() >= concertDto.getHeure().getTime() && c.getHeure().getTime() <= concertDto.getHeure().getTime() + concertDto.getDuree().getTime()) {
                        throw new IllegalArgumentException("Le concert ne peut débuter pendant un autre concert dans la même salle.");
                    }

                    // Si le concert itéré a lieu dans la même salle et le même jour que le concert en paramètre
                    // Et que le concert itéré se termine durant le concert en paramètre
                    // Alors renvoie une erreur appropriée
                    if (concertDto.getHeure().getTime() >= c.getHeure().getTime() && concertDto.getHeure().getTime() <= c.getHeure().getTime() + c.getDuree().getTime()) {
                        throw new IllegalArgumentException("Le concert ne peut se terminer pendant un autre concert dans la même salle.");
                    }
                }
            }
        }
        // Si les heures et dates correspondent alors on les modifies
        concert.setDate(concertDto.getDate());
        concert.setDuree(concertDto.getDuree());
        concert.setHeure(concertDto.getHeure());
        concertRepository.save(concert);
        concertDto1 = this.concertEntityToDto(concert);
        return concertDto1;
    }


	/**
    * Convertit un objet Concert en un objet ConcertDto.
    * @param concert l'objet Concert à convertir
    * @return un objet ConcertDto correspondant à l'objet Concert converti
    */
    private ConcertDto concertEntityToDto(Concert concert){
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
    * @param concertDto l'objet ConcertDto à convertir
    * @return un objet Concert correspondant à l'objet Concert converti
    */
    private Concert concertDtoToEntity(ConcertDto concertDto) {
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
