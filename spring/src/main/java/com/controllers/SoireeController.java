package com.controllers;

import com.dtos.SalleDto;
import com.dtos.SoireeDto;
import com.services.SoireeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/soirees")
public class SoireeController {

    private final SoireeService soireeService;

    public SoireeController(SoireeService soireeService) {
        this.soireeService = soireeService;
    }

    /**
     * <p>Get all Soirees in the system</p>
     * @return List<SoireeDto>
     */
    @GetMapping
    public List<SoireeDto> getSoirees() {
        return soireeService.getAllSoiree();
    }

    /**
     * Method to get the soiree based on the ID
     */
    @GetMapping("/{id}")
    public SoireeDto getSoiree(@PathVariable int id){
        return soireeService.getSoireeById(id);
    }

    /**
     * Create a new soiree in the system
     */
    @PostMapping
    public SoireeDto saveSoiree(final @RequestBody SoireeDto soireeDto){
        return soireeService.saveSoiree(soireeDto);
    }

    /**
     * Delete a soiree by its id
     */
    @DeleteMapping("/{id}")
    public Boolean deleteSoiree(@PathVariable int id){
        return soireeService.deleteSoiree(id);
    }

    @PutMapping("/{id}")
    public SoireeDto updateSoiree(@PathVariable int id, final @RequestBody SoireeDto soireeDto) {
        return soireeService.updateSoiree(id, soireeDto);
    }




}
