package com.services.impl;

import com.dtos.GroupeDto;
import com.entities.Groupe;
import com.repositories.ArtisteRepository;
import com.repositories.GroupeRepository;
import com.services.GroupeService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service("groupeService")

public class GroupeServiceImpl implements GroupeService {

    private final GroupeRepository groupeRepository;
    private  final ArtisteRepository artisteRepository;


    public GroupeServiceImpl(GroupeRepository groupeRepository, ArtisteRepository artisteRepository) {
        this.groupeRepository = groupeRepository;
        this.artisteRepository = artisteRepository;
    }


    /**
     * @param groupeDto
     * @return
     */
    @Override
    public GroupeDto saveGroupe(GroupeDto groupeDto) {
        return GroupeEntityToDto(groupeRepository.save(GroupeDaoToEntity(groupeDto)));
    }

    /**
     * @param groupeId
     * @return
     */
    @Override
    public GroupeDto getGroupeById(int groupeId) {
        return GroupeEntityToDto(groupeRepository.getById(groupeId));
    }

    /**
     * @param groupeId
     * @return
     */
    @Override
    public boolean deleteGroupe(int groupeId) {
        groupeRepository.deleteById(groupeId);
        return true;
    }

    /**
     * @return
     */
    @Override
    public List<GroupeDto> getAllGroupe() {
        List<GroupeDto> groupeDtoList = new ArrayList<>();
        List<Groupe> groupeList = groupeRepository.findAll();
        for (Groupe groupe : groupeList)
            groupeDtoList.add(GroupeEntityToDto(groupe));
        return groupeDtoList;
    }

    private GroupeDto GroupeEntityToDto(Groupe groupe){
        GroupeDto groupeDto = new GroupeDto();
        groupeDto.setIdGroupe(groupe.getIdGroupe());
        groupeDto.setNom(groupe.getNom());
        groupeDto.setNbBillets(groupe.getNbArtistes());
        return groupeDto;
    }

    private Groupe GroupeDaoToEntity(GroupeDto groupeDto) {
        Groupe groupe = new Groupe();
        groupe.setIdGroupe(groupeDto.getIdGroupe());
        groupe.setNom(groupeDto.getNom());
        groupe.setNbArtistes(groupeDto.getNbBillets());
        return groupe;
    }
}
