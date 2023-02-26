package com.services.impl;

import com.dtos.BilletDto;
import com.entities.Billet;
import com.repositories.BilletRepository;
import com.services.BilletService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("BilletService")
public class BilletServiceImpl implements BilletService {

    private final BilletRepository billetRepository;

    public BilletServiceImpl(BilletRepository billetRepository) {
        this.billetRepository = billetRepository;
    }


    /**
     * @param billetDto
     * @return
     */
    @Override
    public BilletDto saveBillet(BilletDto billetDto) {
        Billet billet = new Billet();
        billet = billetRepository.save(BilletDaoToEntity(billetDto));
        return BilletEntityToDto(billet);
    }

    /**
     * @param artisteId
     * @return
     */
    @Override
    public BilletDto getBilletById(int artisteId) {
        return BilletEntityToDto(billetRepository.getById(artisteId));
    }

    /**
     * @param billetId
     * @return
     */
    @Override
    public boolean deleteBillet(int billetId) {
        billetRepository.deleteById(billetId);
        return true;
    }

    /**
     * @return
     */
    @Override
    public List<BilletDto> getAllBillets() {
        List<BilletDto> billetDtoList = new ArrayList<>();
        List<Billet> billetList = billetRepository.findAll();
        for (Billet billet : billetList){
            billetDtoList.add(BilletEntityToDto(billet));
        }
        return billetDtoList;
    }

    private BilletDto BilletEntityToDto(Billet billet){
        BilletDto billetDto = new BilletDto();
        billetDto.setIdBillet(billet.getIdBillet());
        billetDto.setPrix(billet.getPrix());
        billetDto.setIdSoiree(billet.getIdSoiree().getIdSoiree());
        billetDto.setIdUtilisateur(billet.getIdUtilisateur().getIdUtilisateur());
        return billetDto;
    }

    private Billet BilletDaoToEntity(BilletDto billetDto) {
        Billet billet = new Billet();
        billet.setIdBillet(billetDto.getIdBillet());
        billet.setPrix(billet.getPrix());
        billet.getIdSoiree().setIdSoiree((billetDto.getIdSoiree()));
        billet.getIdUtilisateur().setIdUtilisateur(billetDto.getIdUtilisateur());
        return billet;
    }
}
