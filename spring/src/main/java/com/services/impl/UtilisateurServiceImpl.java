package com.services.impl;

import com.dtos.UtilisateurDto;
import com.entities.Utilisateur;
import com.repositories.UtilisateurRepository;
import com.services.UtilisateurService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service("utilisateurService")

public class UtilisateurServiceImpl implements UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;

    public UtilisateurServiceImpl(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }


    /**
     * @param UtilisateurDto
     * @return
     */
    @Override
    public UtilisateurDto saveUtilisateur(UtilisateurDto utilisateurDto) {
        Utilisateur utilisateur = UtilisateurDaoToEntity(utilisateurDto);
        utilisateur = utilisateurRepository.save(utilisateur);
        return UtilisateurEntityToDto(utilisateur);
    }

    /**
     * @param utililisateurId
     * @return
     */
    @Override
    public UtilisateurDto getUtilisateurById(int utililisateurId) {
        return UtilisateurEntityToDto(utilisateurRepository.getById(utililisateurId));
    }


    /**
     * @param utililisateurId
     * @return
     */
    @Override
    public boolean deleteUtilisateur(int utililisateurId) {
        utilisateurRepository.deleteById(utililisateurId);
        return true;
    }

    /**
     * @return
     */
    @Override
    public List<UtilisateurDto> getAllUtilisateurs() {
        List<UtilisateurDto> utilisateurDtosList = new ArrayList<>();
        List<Utilisateur> concertEntityList = utilisateurRepository.findAll();
        for (Utilisateur concert : concertEntityList){
            utilisateurDtosList.add(UtilisateurEntityToDto(concert));
        }
        return utilisateurDtosList;
    }

    private UtilisateurDto UtilisateurEntityToDto(Utilisateur utilisateur){
        UtilisateurDto utilisateurDto = new UtilisateurDto();
        utilisateurDto.setIdUtilisateur(utilisateur.getIdUtilisateur());
        utilisateurDto.setNom(utilisateur.getNom());
        utilisateurDto.setMdp(utilisateur.getMdp());
        return utilisateurDto;
    }

    private Utilisateur UtilisateurDaoToEntity(UtilisateurDto utilisateurDto) {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setIdUtilisateur(utilisateurDto.getIdUtilisateur());
        utilisateur.setNom(utilisateurDto.getNom());
        utilisateur.setMdp(utilisateurDto.getMdp());
        return utilisateur;
    }
}
