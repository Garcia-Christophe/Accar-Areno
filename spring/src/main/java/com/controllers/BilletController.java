package com.controllers;

import com.dtos.BilletDto;
import com.services.BilletService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
Classe de contrôleur pour gérer les billets
 */
@RestController
@RequestMapping("/billets")
public class BilletController {

    private final BilletService billetService ;
    /**
     Constructeur pour injecter l'instance BilletService
     */
    public BilletController(BilletService billetService) {
        this.billetService = billetService;
    }


    /**
     * <p>Get pour récupérer tous les billets du système</p>
     * @return List<BilletDto>
     */

    @GetMapping
    public List<BilletDto> getBillets() {
        return billetService.getAllBillets();
    }

    /**
     * Methode pour récupérer un billet en fonction de son ID
     */
    @GetMapping("/{id}")
    public BilletDto getBillet(@PathVariable int id){
        return billetService.getBilletById(id);
    }

    /**
     * pour créer un nouveau billet dans le système
     */
    @PostMapping
    public BilletDto saveBillet(final @RequestBody BilletDto billetDto){
        return billetService.saveBillet(billetDto);
    }

    /**
     *  pour supprimer un billet en fonction de son ID
     */
    @DeleteMapping("/{id}")
    public Boolean deleteBillet(@PathVariable int id){
        return billetService.deleteBillet(id);
    }

}
