package com.services;

import com.dtos.BilletDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface BilletService {
    /**
     * Sauve a billet
     */
    BilletDto saveBillet(BilletDto billetDto);

    /**
     * Get a billet by it's id
     */
    BilletDto getBilletById(int artisteId);

    /**
     * Delete a billet by it's id
     */
    boolean deleteBillet(int billetId);

    /**
     * Get all the billets
     */
    List<BilletDto> getAllBillets();


}
