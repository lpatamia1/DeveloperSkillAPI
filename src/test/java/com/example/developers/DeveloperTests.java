package com.example.developers;

import com.devbase.dto.DeveloperDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * ✅ DeveloperTests.java — Integration test for API + service layers.
 */
@SpringBootTest
@AutoConfigureMockMvc
public class DeveloperTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DeveloperService service;

    @Autowired
    private ObjectMapper mapper;

    private DeveloperDTO sampleDTO;

    @BeforeEach
    void setUp() {
        sampleDTO = new DeveloperDTO();
        sampleDTO.setId(1L);
        sampleDTO.setName("Lily");
        sampleDTO.setTitle("Backend Developer");
        sampleDTO.setSkills(List.of("Java", "Spring Boot"));
        sampleDTO.setCertifications(List.of("Oracle Java SE"));
    }

    // 🧩 Context loads successfully
    @Test
    void contextLoads() {
        assertThat(service).isNotNull();
    }

    // 🧠 Test GET /api/developers
    @Test
    void testGetAllDevelopers() throws Exception {
        when(service.getAllDevelopers()).thenReturn(List.of(sampleDTO));

        mockMvc.perform(get("/api/developers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Lily"))
                .andExpect(jsonPath("$[0].title").value("Backend Developer"));
    }

    // ➕ Test POST /api/developers
    @Test
    void testAddDeveloper() throws Exception {
        when(service.saveDeveloper(Mockito.any(DeveloperDTO.class))).thenReturn(sampleDTO); // ✅ fixed method name

        mockMvc.perform(post("/api/developers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(sampleDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Lily"))
                .andExpect(jsonPath("$.skills[0]").value("Java"));
    }

    // 🧾 Test GET /api/developers/{id}
    @Test
    void testGetDeveloperById() throws Exception {
        when(service.getDeveloperById(1L)).thenReturn(sampleDTO);

        mockMvc.perform(get("/api/developers/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Lily"));
    }

    // ❌ Test DELETE /api/developers/{id}
    @Test
    void testDeleteDeveloper() throws Exception {
        doNothing().when(service).deleteDeveloper(1L);

        mockMvc.perform(delete("/api/developers/1"))
                .andExpect(status().isOk());
    }
}
