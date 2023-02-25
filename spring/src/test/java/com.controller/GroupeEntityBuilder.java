package com.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class GroupeBuilder {
    public static List<String> getIds() {
        return Collections.singletonList("1");
    }

    public static GroupeDto getDto() {
        GroupeDto dto = new GroupeDto();
        dto.setId("1");
        return dto;
    }
}