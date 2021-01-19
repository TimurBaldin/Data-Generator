package com.rufus.bumblebee.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rufus.bumblebee.controllers.responses.BaseResponse;
import com.rufus.bumblebee.controllers.responses.ContainerDto;
import com.rufus.bumblebee.repository.ContainerStatus;
import com.rufus.bumblebee.services.ContainerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ContainerController.class)
public class ContainerControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ContainerService service;

    private static final Gson gson = new GsonBuilder().serializeNulls().create();
    private static final String TEST_VALUE = "TEST";

    @Test
    public void testAddContainer() throws Exception {
        BaseResponse<ContainerDto> baseResponse = new BaseResponse<>();
        ContainerDto dto = getContainerDto();
        baseResponse.setResponse(dto);
        given(service.createTestDataContainer(TEST_VALUE)).willReturn(dto);

        MockHttpServletResponse response = mvc.perform(post("/containerManager/add/" + TEST_VALUE)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).
                        andReturn().getResponse();
        assertEquals(response.getContentAsString(), gson.toJson(baseResponse));
    }

    @Test
    public void testRemoveContainer() throws Exception {
        MockHttpServletResponse response = mvc.perform(delete("/containerManager/remove/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).
                        andReturn().getResponse();
        assertEquals(response.getContentAsString(), gson.toJson(new BaseResponse<>()));
    }

    private ContainerDto getContainerDto() {
        ContainerDto dto = new ContainerDto();
        dto.setId(1L);
        dto.setName(TEST_VALUE);
        dto.setStatus(ContainerStatus.NEW);
        return dto;
    }

}