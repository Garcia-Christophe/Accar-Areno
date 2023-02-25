package com.services;

import com.dtos.ArtisteDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ArtisteService {
    /**
     * Sauve a artiste
     */
    ArtisteDto saveArtiste(ArtisteDto artisteDto);

    /**
     * Get a artiste by it's id
     */
    ArtisteDto getArtisteById(int artisteId);

    /**
     * Delete a artiste by it's id
     */
    boolean deleteArtiste(int artisteId);

    /**
     * Get all the artistes
     */
    List<ArtisteDto> getAllArtistes();


}
