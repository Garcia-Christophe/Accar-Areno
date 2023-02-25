package com.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class ArtisteBuilder {
    public static List<String> getIds() {
        return Collections.singletonList("1");
    }

    public static ArtisteDto getDto() {
        ArtisteDto dto = new ArtisteDto();
        dto.setId("1");
        return dto;
    }
}