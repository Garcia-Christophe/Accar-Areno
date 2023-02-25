package com.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class DogBuilder {
    public static List<String> getIds() {
        return Collections.singletonList("1");
    }

    public static DogDto getDto() {
        DogDto dto = new DogDto();
        dto.setId("1");
        return dto;
    }
}