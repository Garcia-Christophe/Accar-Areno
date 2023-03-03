package com.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class ConcertBuilder {
    public static List<String> getIds() {
        return Collections.singletonList("1");
    }

    public static ConcertDto getDto() {
        ConcertDto dto = new ConcertDto();
        dto.setId("1");
        return dto;
    }
}