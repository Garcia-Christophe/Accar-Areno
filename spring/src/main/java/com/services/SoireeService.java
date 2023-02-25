package com.services;


import com.dtos.SoireeDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface SoireeService {

    SoireeDto saveSoiree(SoireeDto soireeDto);
    SoireeDto getSoireeById(int salleId);

    boolean deleteSoiree(int salleId);
    List<SoireeDto> getAllSoiree();
    SoireeDto updateSoiree(int soireeId, SoireeDto soireeDto);
}
