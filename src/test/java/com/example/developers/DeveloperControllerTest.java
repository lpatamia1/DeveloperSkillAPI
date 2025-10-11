package com.example.developers;

import com.devbase.dto.DeveloperDTO;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DeveloperController.class)
public class DeveloperControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DeveloperService service;

    @Test
    void testGetAllDevelopers() throws Exception {
        DeveloperDTO dev = new DeveloperDTO();
        dev.setName("Lily");
        dev.setTitle("Full Stack Developer");
        dev.setSkills(List.of("Java", "Spring Boot"));

        when(service.getAllDevelopers()).thenReturn(List.of(dev));

        mockMvc.perform(get("/api/developers")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Lily"))
                .andExpect(jsonPath("$[0].title").value("Full Stack Developer"));
    }
}
