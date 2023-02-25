package com.controller;

import com.dto.DogDto;
import com.mapper.DogMapper;
import com.mapper.EntityMapper;
import com.service.DogService;
import org.hamcrest.Matchers;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Transactional
public class DogControllerTest {
    private static final String ENDPOINT_URL = "/api/dog";
    @InjectMocks
    private DogController dogController;
    @Mock
    private DogService dogService;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(dogController)
                //.setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                //.addFilter(CustomFilter::doFilter)
                .build();
    }

    @Test
    public void findAllByPage() throws Exception {
        Page<DogDto> page = new PageImpl<>(Collections.singletonList(DogBuilder.getDto()));

        Mockito.when(dogService.findByCondition(ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(page);

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.content", Matchers.hasSize(1)));

        Mockito.verify(dogService, Mockito.times(1)).findByCondition(ArgumentMatchers.any(), ArgumentMatchers.any());
        Mockito.verifyNoMoreInteractions(dogService);

    }

    @Test
    public void getById() throws Exception {
        Mockito.when(dogService.findById(ArgumentMatchers.anyLong())).thenReturn(DogBuilder.getDto());

        mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL + "/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Is.is(1)));
        Mockito.verify(dogService, Mockito.times(1)).findById("1");
        Mockito.verifyNoMoreInteractions(dogService);
    }

    @Test
    public void save() throws Exception {
        Mockito.when(dogService.save(ArgumentMatchers.any(DogDto.class))).thenReturn(DogBuilder.getDto());

        mockMvc.perform(
                        MockMvcRequestBuilders.post(ENDPOINT_URL)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(CustomUtils.asJsonString(DogBuilder.getDto())))
                .andExpect(MockMvcResultMatchers.status().isCreated());
        Mockito.verify(dogService, Mockito.times(1)).save(ArgumentMatchers.any(DogDto.class));
        Mockito.verifyNoMoreInteractions(dogService);
    }

    @Test
    public void update() throws Exception {
        Mockito.when(dogService.update(ArgumentMatchers.any(), ArgumentMatchers.anyLong())).thenReturn(DogBuilder.getDto());

        mockMvc.perform(
                        MockMvcRequestBuilders.put(ENDPOINT_URL + "/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(CustomUtils.asJsonString(DogBuilder.getDto())))
                .andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(dogService, Mockito.times(1)).update(ArgumentMatchers.any(DogDto.class), ArgumentMatchers.anyLong());
        Mockito.verifyNoMoreInteractions(dogService);
    }

    @Test
    public void delete() throws Exception {
        Mockito.doNothing().when(dogService).deleteById(ArgumentMatchers.anyLong());
        mockMvc.perform(
                MockMvcRequestBuilders.delete(ENDPOINT_URL + "/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(CustomUtils.asJsonString(DogBuilder.getIds()))).andExpect(MockMvcResultMatchers.status().isOk());
        Mockito.verify(dogService, Mockito.times(1)).deleteById(Mockito.anyLong());
        Mockito.verifyNoMoreInteractions(dogService);
    }
}