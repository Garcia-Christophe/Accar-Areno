package com.controller;

import com.controller.CustomUtils;
import com.controllers.ConcertController;
import com.dto.ConcertDto;
import com.entities.Concert;
import com.fasterxml.jackson.databind.util.BeanUtil;
import com.mapper.ConcertMapper;
import com.mapper.EntityMapper;
import com.service.ConcertService;
import org.hamcrest.Matchers;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collections;

@Transactional
public class ConcertControllerTest {
    private static final String ENDPOINT_URL = "/api/concert";
    @InjectMocks
    private ConcertController concertController;
    @Mock
    private ConcertService concertService;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(concertController)
                //.setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                //.addFilter(CustomFilter::doFilter)
                .build();
    }

    @Test
    public void findAllByPage() throws Exception {
        Page<ConcertDto> page = new PageImpl<>(Collections.singletonList(ConcertBuilder.getDto()));

        Mockito.when(concertService.findByCondition(ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(page);

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.content", Matchers.hasSize(1)));

        Mockito.verify(concertService, Mockito.times(1)).findByCondition(ArgumentMatchers.any(), ArgumentMatchers.any());
        Mockito.verifyNoMoreInteractions(concertService);

    }

    @Test
    public void getById() throws Exception {
        Mockito.when(concertService.findById(ArgumentMatchers.anyInteger())).thenReturn(ConcertBuilder.getDto());

        mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL + "/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Is.is(1)));
        Mockito.verify(concertService, Mockito.times(1)).findById("1");
        Mockito.verifyNoMoreInteractions(concertService);
    }

    @Test
    public void save() throws Exception {
        Mockito.when(concertService.save(ArgumentMatchers.any(ConcertDto.class))).thenReturn(ConcertBuilder.getDto());

        mockMvc.perform(
                        MockMvcRequestBuilders.post(ENDPOINT_URL)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(CustomUtils.asJsonString(ConcertBuilder.getDto())))
                .andExpect(MockMvcResultMatchers.status().isCreated());
        Mockito.verify(concertService, Mockito.times(1)).save(ArgumentMatchers.any(ConcertDto.class));
        Mockito.verifyNoMoreInteractions(concertService);
    }

    @Test
    public void update() throws Exception {
        Mockito.when(concertService.update(ArgumentMatchers.any(), ArgumentMatchers.anyInteger())).thenReturn(ConcertBuilder.getDto());

        mockMvc.perform(
                        MockMvcRequestBuilders.put(ENDPOINT_URL + "/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(CustomUtils.asJsonString(ConcertBuilder.getDto())))
                .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(concertService, Mockito.times(1)).update(ArgumentMatchers.any(ConcertDto.class), ArgumentMatchers.anyInteger());
        Mockito.verifyNoMoreInteractions(concertService);
    }

    @Test
    public void delete() throws Exception {
        Mockito.doNothing().when(concertService).deleteById(ArgumentMatchers.anyInteger());
        mockMvc.perform(
                MockMvcRequestBuilders.delete(ENDPOINT_URL + "/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(CustomUtils.asJsonString(ConcertBuilder.getIds()))).andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(concertService, Mockito.times(1)).deleteById(Mockito.anyInteger());
        Mockito.verifyNoMoreInteractions(concertService);
    }
}