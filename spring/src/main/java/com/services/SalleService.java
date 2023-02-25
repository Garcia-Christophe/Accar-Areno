package com.services;


import com.dtos.SalleDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface SalleService {

    SalleDto saveSalle(SalleDto salleDto);
    SalleDto getSalleById(int salleId);

    boolean deleteSalle(int salleId);
    List<SalleDto> getAllSalles();

    SalleDto updateSalle(int salleId, SalleDto salleDto);




}
