package com.controllers;

import com.dtos.GroupeDto;
import com.services.GroupeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/groupes")
public class GroupeController {

    private final GroupeService groupeService;

    public GroupeController(GroupeService groupeService) {
        this.groupeService = groupeService;
    }

    /**
     * <p>Get all Groupes in the system</p>
     * @return List<GroupeDto>
     */
    @GetMapping
    public List<GroupeDto> getGroupes() {
        return groupeService.getAllGroupe();
    }

    /**
     * Method to get the groupe based on the ID
     */
    @GetMapping("/{id}")
    public GroupeDto getGroupe(@PathVariable int id){
        return groupeService.getGroupeById(id);
    }

    /**
     * Create a new groupe in the system
     */
    @PostMapping
    public GroupeDto saveGroupe(final @RequestBody GroupeDto groupeDto){
        return groupeService.saveGroupe(groupeDto);
    }

    /**
     * Delete a groupe by its id
     */
    @DeleteMapping("/{id}")
    public Boolean deleteGroupe(@PathVariable int id){
        return groupeService.deleteGroupe(id);
    }

}
