package com.controllers;

import com.dtos.SalleDto;
import com.services.SalleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/salles")
@CrossOrigin(origins = "http://localhost:8081")
public class SalleController {

    private final SalleService salleService;

    public SalleController(SalleService salleService) {
        this.salleService = salleService;
    }

    /**
     * <p>Get all Salles in the system</p>
     * @return List<SalleDto>
     */
    @GetMapping
    public List<SalleDto> getSalles() {
        return salleService.getAllSalles();
    }

    /**
     * Method to get the salle based on the ID
     */
    @GetMapping("/{id}")
    public SalleDto getSalle(@PathVariable int id){
        return salleService.getSalleById(id);
    }

    /**
     * Create a new salle in the system
     */
    @PostMapping
    public SalleDto saveSalle(final @RequestBody SalleDto salleDto){
        return salleService.saveSalle(salleDto);
    }

    /**
     * Delete a salle by its id
     */
    @DeleteMapping("/{id}")
    public Boolean deleteSalle(@PathVariable int id){
        return salleService.deleteSalle(id);
    }

    /**
     * Update an existing salle in the system
     */
    @PutMapping("/{id}")
    public SalleDto updateSalle(@PathVariable int id, final @RequestBody SalleDto salleDto) {
        return salleService.updateSalle(id, salleDto);
    }


}
