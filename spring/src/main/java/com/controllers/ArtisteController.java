package com.controllers;

import com.dtos.ArtisteDto;
import com.services.ArtisteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/artistes")
public class ArtisteController {

    private final ArtisteService artisteService;

    public ArtisteController(ArtisteService artisteService) {
        this.artisteService = artisteService;
    }


    /**
     * <p>Get all Artiste in the system</p>
     * @return List<ArtisteDto>
     */
    @GetMapping
    public List<ArtisteDto> getArtistes() {
        return artisteService.getAllArtistes();
    }

    /**
     * Method to get the artiste based on the ID
     */
    @GetMapping("/{id}")
    public ArtisteDto getArtiste(@PathVariable int id){
        return artisteService.getArtisteById(id);
    }

    /**
     * Create a new artiste in the system
     */
    @PostMapping
    public ArtisteDto saveArtiste(final @RequestBody ArtisteDto artisteDto){
        return artisteService.saveArtiste(artisteDto);
    }

    /**
     * Delete a artiste by it's id
     */
    @DeleteMapping("/{id}")
    public Boolean deleteArtiste(@PathVariable int id){
        return artisteService.deleteArtiste(id);
    }

}
