package com.services;

import com.dtos.ConcertDto;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ConcertService {

    ConcertDto saveConcert(ConcertDto concertDto);
    ConcertDto getConcertById(int concertId);

    boolean deleteConcert(int concertId);
    List<ConcertDto> getAllConcerts();
    ConcertDto updateConcert(int concertId, ConcertDto concertDto);
}
