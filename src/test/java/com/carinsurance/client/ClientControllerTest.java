package com.carinsurance.client;

import com.carinsurance.client.dto.ClientRequestDto;
import com.carinsurance.client.dto.ClientResponseDto;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.shaded.com.fasterxml.jackson.core.JsonProcessingException;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(
        controllers = ClientController.class
)

class ClientControllerTest {
    @MockBean
    private ClientService clientService;
    @Autowired
    private MockMvc mockMvc;


    private static ClientRequestDto clientRequestDto;
    private static String clientRequestDtoJson;
    private static Client client;
    private static ClientResponseDto clientResponseDto;


    @BeforeEach
    void setUp() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        clientRequestDto = new ClientRequestDto("John", "Smith", 30, null);
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        clientRequestDtoJson = objectMapper.writeValueAsString(clientRequestDto);
        clientResponseDto = new ClientResponseDto(1L, "John", "Smith", 30, null);

    }

    @Test
    void saveClient() throws Exception {
        BDDMockito.given(clientService.saveClient(clientRequestDto))
                .willReturn(client);

        mockMvc.perform(post("/clients/add")
                        .content(clientRequestDtoJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void findClientById() throws Exception {
        BDDMockito.given(clientService.findClientById(1L)).willReturn(clientResponseDto);

        mockMvc.perform(get("/clients/1"))
                .andExpect(jsonPath("$.id", Matchers.is(1)))
                .andExpect(jsonPath("$.firstName", Matchers.is("John")))
                .andExpect(jsonPath("$.lastName", Matchers.is("Smith")))
                .andExpect(jsonPath("$.age", Matchers.is(30)))
                .andExpect(status().is(200));
    }


    @Test
    void addClientToCar() {
    }
}