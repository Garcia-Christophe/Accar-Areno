package com.services.impl;

import com.dtos.ArtisteDto;
import com.entities.Artiste;
import com.repositories.ArtisteRepository;
import com.services.ArtisteService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service("ArtisteServic")
public class ArtisteServiceImpl implements ArtisteService {

    private final ArtisteRepository artisteRepository;

    public ArtisteServiceImpl(ArtisteRepository artisteRepository) {
        this.artisteRepository = artisteRepository;
    }

    /**
     * @param artisteDto
     * @return
     */
    @Override
    public ArtisteDto saveArtiste(ArtisteDto artisteDto) {
        Artiste artiste = ArtisteDaoToEntity(artisteDto);
        artiste = artisteRepository.save(artiste);
        return ArtisteEntityToDao(artiste);
    }

    /**
     * @param artisteId
     * @return
     */
    @Override
    public ArtisteDto getArtisteById(int artisteId) {
        return ArtisteEntityToDao(artisteRepository.getById(artisteId));
    }

    /**
     * @param artisteId
     * @return
     */
    @Override
    public boolean deleteArtiste(int artisteId) {
        artisteRepository.deleteById(artisteId);
        return true;
    }

    /**
     * @return
     */
    @Override
    public List<ArtisteDto> getAllArtistes() {
        List<ArtisteDto> artisteDtoList = new ArrayList<>();
        List<Artiste> artisteList = artisteRepository.findAll();
        for (Artiste art : artisteList){
            artisteDtoList.add(ArtisteEntityToDao(art));
        }
        return artisteDtoList;
    }

    private ArtisteDto ArtisteEntityToDao(Artiste artiste){
        ArtisteDto artisteDto = new ArtisteDto();
        artisteDto.setIdGroupe(artiste.getIdArtiste());
        artisteDto.setNom(artiste.getNom());
        artisteDto.setVilleOrigine(artiste.getVilleOrigine());
        artisteDto.setDateNaissance(artiste.getDateNaissance());
        artisteDto.setIdGroupe(artiste.getGroupe().getIdGroupe());
        return artisteDto;
    }

    private Artiste ArtisteDaoToEntity(ArtisteDto artisteDto) {
        Artiste artiste = new Artiste();
        artiste.setIdArtiste(artisteDto.getIdArtiste());
        artiste.setNom(artisteDto.getNom());
        artiste.setVilleOrigine(artisteDto.getVilleOrigine());
        artiste.setDateNaissance(artisteDto.getDateNaissance());
        artiste.getGroupe().setIdGroupe(artisteDto.getIdArtiste());
        return artiste;
    }
}
