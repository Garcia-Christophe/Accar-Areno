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
     * @param salleDto
     * @return
     */
    @Override
    public SalleDto saveSalle(SalleDto salleDto) {
        /*SalleEntity salleEntity = new SalleEntity();
        salleEntity = salleRepository.save(SalleDtoToEntity(salleDto));
        return SalleEntityToDto(salleEntity);*/
        Salle salle = salleRepository.save(SalleDtoToEntity(salleDto));
        return SalleEntityToDto(salle);
    }

    /**
     * @param salleId
     * @return
     */
    @Override
    public SalleDto getSalleById(int salleId) {
        return SalleEntityToDto(salleRepository.getById(salleId));
    }

    /**
     * @param salleId
     * @return
     */
    @Override
    public boolean deleteSalle(int salleId) {
        List<Soiree> soireeList = soireeRepository.findAll();
        for( Soiree soiree : soireeList)
            if(soiree.getIdSalle().getIdSalle()==salleId)
                soireeService.deleteSoiree(soiree.getIdSoiree());

        salleRepository.deleteById(salleId);
        //salleRepository.de
        return true;
    }

    /**
     * @return
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
     * @param salleId
     * @param salleDto
     * @return
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
