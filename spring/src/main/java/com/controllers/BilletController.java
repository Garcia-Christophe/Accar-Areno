package com.controllers;

import com.dtos.BilletDto;
import com.services.BilletService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/billets")
public class BilletController {

    private final BilletService billetService ;

    public BilletController(BilletService billetService) {
        this.billetService = billetService;
    }


    /**
     * <p>Get all Billet in the system</p>
     * @return List<BilletDto>
     */
    @GetMapping
    public List<BilletDto> getBillets() {
        return billetService.getAllBillets();
    }

    /**
     * Method to get the billet based on the ID
     */
    @GetMapping("/{id}")
    public BilletDto getBillet(@PathVariable int id){
        return billetService.getBilletById(id);
    }

    /**
     * Create a new billet in the system
     */
    @PostMapping
    public BilletDto saveBillet(final @RequestBody BilletDto billetDto){
        return billetService.saveBillet(billetDto);
    }

    /**
     * Delete a billet by it's id
     */
    @DeleteMapping("/{id}")
    public Boolean deleteBillet(@PathVariable int id){
        return billetService.deleteBillet(id);
    }

}
