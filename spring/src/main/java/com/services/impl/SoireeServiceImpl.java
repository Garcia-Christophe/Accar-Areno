package com.services.impl;

import com.dtos.SoireeDto;
import com.entities.Salle;
import com.entities.Soiree;
import com.repositories.BilletRepository;
import com.repositories.SoireeRepository;
import com.services.SoireeService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service("soireeService")

public class SoireeServiceImpl implements SoireeService {

    private final SoireeRepository soireeRepository ;
    private final BilletRepository billetRepository ;


    public SoireeServiceImpl(SoireeRepository soireeRepository, BilletRepository billetRepository) {
        this.soireeRepository = soireeRepository;
        this.billetRepository = billetRepository;
    }

    /**
     * @param soireeDto
     * @return
     */
    @Override
    public SoireeDto saveSoiree(SoireeDto soireeDto) {
        Soiree soiree = new Soiree();
        soiree = soireeRepository.save(SoireeDtoToEntity(soireeDto));
        return SoireeEntityToDto(soiree);
    }

    /**
     * @param soireeId
     * @return
     */
    @Override
   /* public SoireeDto getSoireeById(int soireeId) {
        return SoireeEntityToDto(soireeRepository.getById(soireeId));
    }*/
    public SoireeDto getSoireeById(int soireeId) {
        Soiree soiree = soireeRepository.findById(soireeId).orElseThrow(() ->
                new IllegalArgumentException("Invalid Soiree ID: " + soireeId));
        return SoireeEntityToDto(soiree);
    }
    /**
     * @param soireeId
     * @return
     */
    @Override
    public boolean deleteSoiree(int soireeId) {

        soireeRepository.deleteById(soireeId);
        return true;

    }

    /**
     * @return
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
     * @param soireeId
     * @param soireeDto
     * @return
     */
    @Override
    public SoireeDto updateSoiree(int soireeId, SoireeDto soireeDto) {
        SoireeDto soireeDto1 = new SoireeDto();
        soireeDto1.setNom(soireeDto.getNom());
        soireeDto1.setIdSoiree(soireeDto.getIdSoiree());
        soireeDto1.setIdSalle(soireeDto.getIdSalle());
        return saveSoiree(soireeDto1);
    }


    private SoireeDto SoireeEntityToDto(Soiree soiree){
        SoireeDto soireeDto = new SoireeDto();
        soireeDto.setIdSoiree(soiree.getIdSoiree());
        soireeDto.setIdSalle(soiree.getIdSalle().getIdSalle());
        soireeDto.setNom(soiree.getNom());
        return soireeDto;
    }

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
