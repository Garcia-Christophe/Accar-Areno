package com.services;


import com.dtos.GroupeDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface GroupeService {

    GroupeDto saveGroupe(GroupeDto groupeDto );
    GroupeDto getGroupeById(int groupeId);

    boolean deleteGroupe(int groupeId);
    List<GroupeDto> getAllGroupe();
}
